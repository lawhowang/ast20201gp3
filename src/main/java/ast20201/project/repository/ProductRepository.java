package ast20201.project.repository;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ast20201.project.model.Product;
import ast20201.project.model.ProductFilter;
import ast20201.project.model.Category;
import ast20201.project.model.PageData;

@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PageData<Product> getProducts(int page, ProductFilter filter, String sortBy) {
        String query = "SELECT SQL_CALC_FOUND_ROWS product.id, product.name, product.price, product.quantity, product.description, product.image FROM product\n"
                + "LEFT JOIN product_category ON product.id = product_category.product_id\n";
        List<Object> params = new ArrayList<Object>();

        // Apply filter
        if (null != filter) {
            List<String> condition = new ArrayList<String>();
            if (null != filter.getName()) {
                condition.add("product.name LIKE ?");
                params.add('%' + filter.getName() + '%');
            }
            if (null != filter.getPriceLowerBound()) {
                condition.add("product.price >= ");
                params.add(filter.getPriceLowerBound());
            }
            if (null != filter.getPriceUpperBound()) {
                condition.add("product.price <= ");
                params.add(filter.getPriceUpperBound());
            }
            if (null != filter.getCategory()) {
                condition.add(
                        "product.id IN (SELECT product_id FROM product_category INNER JOIN category ON category.id = product_category.category_id WHERE category.id = ? ) ");
                params.add(filter.getCategory());
            }
            if (condition.size() > 0) {
                query += " WHERE product.deleted = 0 AND " + String.join(" AND ", condition) + " ";
            } else {
                query += "WHERE product.deleted = 0 ";
            }
        } else {
            query += "WHERE product.deleted = 0 ";
        }
        query += "GROUP BY product.id ";

        // Order
        if (null != sortBy) {
            switch (sortBy) {
            case "id":
                query += " ORDER BY id ";
                break;
            case "name":
                query += " ORDER BY name ";
                break;
            case "price":
                query += " ORDER BY price ";
                break;
            }
        }

        // Pagination
        int noOfProductsPerPage = 10;
        int row = (page - 1) * noOfProductsPerPage;
        int offset = noOfProductsPerPage;
        query += " LIMIT ?, ?";
        params.add(row);
        params.add(offset);

        System.out.println(query);

        List<Product> products = jdbcTemplate.query(query, params.toArray(), (ResultSet rs) -> {
            List<Product> result = new ArrayList<Product>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                Integer quantity = (Integer) rs.getObject("quantity");
                String description = rs.getString("description");
                Blob image = rs.getBlob("image");

                List<Category> categories = getProductCategories(id);

                result.add(new Product(id, name, price, quantity, description, categories, image));
            }
            return result;
        });
        int count = jdbcTemplate.queryForObject("SELECT FOUND_ROWS()", Integer.class);

        return new PageData<Product>(products, page, count);
    }

    public Product getProduct(long productId) {
        String query = "SELECT product.id, product.name, product.price, product.quantity, product.description, product.image FROM product\n"
                + "LEFT JOIN product_category ON product.id = product_category.product_id \n"
                + "WHERE product.id = ? GROUP BY product.id";

        Product product = jdbcTemplate.queryForObject(query, new Object[] { productId }, (ResultSet rs, int row) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            BigDecimal price = rs.getBigDecimal("price");
            Integer quantity = (Integer) rs.getObject("quantity");
            String description = rs.getString("description");
            Blob image = rs.getBlob("image");

            List<Category> categories = getProductCategories(id);

            return new Product(id, name, price, quantity, description, categories, image);
        });
        return product;
    }

    public void updateProduct(long id, String name, BigDecimal price, Integer quantity, String description,
            List<Category> categories) {
        // update basic info
        jdbcTemplate.update("UPDATE product SET name = ?, price = ?, quantity = ?, description = ? WHERE id = ?",
                new Object[] { name, price, quantity, description, id });

        // remove categories
        String deleteQuery = "DELETE FROM product_category WHERE product_id = ?";
        if (null != categories && categories.size() > 0) {
            String cats = categories.stream().map(Category::getId).map(Object::toString)
                    .collect(Collectors.joining(", "));
            deleteQuery += " AND category_id NOT IN (" + cats + ")";
        }
        jdbcTemplate.update(deleteQuery, new Object[] { id });

        // insert categories, ingores duplicates as table blocks duplicated primary key
        // internally
        for (Category category : categories) {
            try {
                jdbcTemplate.update("INSERT INTO product_category VALUES (?, ?)",
                        new Object[] { id, category.getId() });
            } catch (Exception ex) {

            }
        }
    }

    public void deleteProduct(long id) {
        jdbcTemplate.update("UPDATE product SET deleted = 1 WHERE id = ?", new Object[] { id });
    }

    public long addProduct(String name, BigDecimal price, Integer quantity, String description,
            List<Category> categories) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO product(name, price, quantity, description) VALUES(?, ?, ?, ?)",
                        new String[] { "id" });
                ps.setString(1, name);
                ps.setBigDecimal(2, price);
                ps.setObject(3, quantity);
                ps.setString(4, description);
                return ps;
            }
        }, keyHolder);

        long pk = keyHolder.getKey().longValue();
        for (Category category : categories) {
            try {
                jdbcTemplate.update("INSERT INTO product_category VALUES (?, ?)",
                        new Object[] { pk, category.getId() });
            } catch (Exception ex) {

            }
        }
        return pk;
    }

    public void updateProductImage(long id, Blob image) {
        jdbcTemplate.update("UPDATE product SET image = ? WHERE id = ?", new Object[] { image, id });
    }

    public int getProductCount() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product", Integer.class);
        return count;
    }

    public List<Category> getProductCategories(long productId) {
        List<Category> categories = jdbcTemplate.query(
                "SELECT * FROM product_category INNER JOIN category ON category.id = product_category.category_id WHERE product_category.product_id = ? ORDER BY level, priority",
                new Object[] { productId }, (ResultSet rs) -> {
                    Map<Long, Category> result = new LinkedHashMap<Long, Category>();
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        String name = rs.getString("name");
                        long parentId = rs.getLong("parent_id");
                        long order = rs.getLong("priority");
                        long level = rs.getLong("level");
                        if (level == 0) {
                            result.put(id, new Category(id, name, order, level));
                        } else {
                            Category parent = result.get(parentId);
                            Category subCategory = new Category(id, name, order, level);
                            if (parent != null)
                                parent.addChildren(subCategory);
                            result.put(id, subCategory); // Keep as an referece in the hash map for adding subcategories
                        }
                    }
                    // Return level 0 category only
                    List<Category> finalResult = new ArrayList<Category>();
                    for (Map.Entry<Long, Category> r : result.entrySet()) {
                        // if (r.getValue().getLevel() == 0)
                        finalResult.add(r.getValue());
                    }
                    return finalResult;
                });
        return categories;
    }

	public List<Product> getLatestProducts() {
		List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE deleted = 0 ORDER BY id LIMIT 0, 12", (ResultSet rs) -> {
            List<Product> result = new ArrayList<Product>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                Integer quantity = (Integer) rs.getObject("quantity");
                String description = rs.getString("description");
                Blob image = rs.getBlob("image");

                List<Category> categories = getProductCategories(id);

                result.add(new Product(id, name, price, quantity, description, categories, image));
            }
            return result;
        });
        return products;
	}
}
