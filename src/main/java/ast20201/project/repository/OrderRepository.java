package ast20201.project.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ast20201.project.model.Order;
import ast20201.project.model.OrderProduct;
import ast20201.project.model.PageData;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Order getOrder(long userId, long orderId) {
        Order order = jdbcTemplate.queryForObject("SELECT * FROM `order` WHERE id = ? AND user_id = ?",
                new Object[] { orderId, userId }, new BeanPropertyRowMapper<Order>(Order.class));
        order.setOrderProducts(getOrderProducts(orderId));
        return order;
    }

    public Order getOrder(long id) {
        Order order = jdbcTemplate.queryForObject("SELECT * FROM `order` WHERE id = ?", new Object[] { id },
                new BeanPropertyRowMapper<Order>(Order.class));
        order.setOrderProducts(getOrderProducts(id));
        return order;
    }

    public PageData<Order> getOrders(String query, int page) {
        int row = (page - 1) * 10;
        int offset = 10;
        query = '%' + query + '%';
        List<Order> orders = jdbcTemplate.query(
                "SELECT SQL_CALC_FOUND_ROWS o.id, o.user_id, o.order_status, o.payment_status, o.create_date, o.last_update_date, o.confirm_date, o.message, o.admin_message FROM `order` o JOIN user ON user.id = o.user_id WHERE o.id LIKE ? OR user.username LIKE ? ORDER BY o.create_date DESC LIMIT ?, ?",
                new Object[] { query, query, row, offset }, new BeanPropertyRowMapper<Order>(Order.class));
        int count = jdbcTemplate.queryForObject("SELECT FOUND_ROWS()", Integer.class);
        for (Order order : orders) {
            order.setOrderProducts(getOrderProducts(order.getId()));
        }
        return new PageData<Order>(orders, page, count);
    }

    public PageData<Order> getOrders(long userId, int page) {
        int row = (page - 1) * 10;
        int offset = 10;
        List<Order> orders = jdbcTemplate.query(
                "SELECT SQL_CALC_FOUND_ROWS * FROM `order` WHERE user_id = ? ORDER BY create_date DESC LIMIT ?, ?",
                new Object[] { userId, row, offset }, new BeanPropertyRowMapper<Order>(Order.class));
        int count = jdbcTemplate.queryForObject("SELECT FOUND_ROWS()", Integer.class);
        for (Order order : orders) {
            order.setOrderProducts(getOrderProducts(order.getId()));
        }
        return new PageData<Order>(orders, page, count);
    }

    public List<OrderProduct> getOrderProducts(long orderId) {
        List<OrderProduct> orderProducts = jdbcTemplate.query("SELECT * FROM order_product WHERE order_id = ?",
                new Object[] { orderId }, (ResultSet rs) -> {
                    List<OrderProduct> results = new ArrayList<OrderProduct>();
                    while (rs.next()) {
                        long productId = rs.getLong("product_id");
                        int amount = rs.getInt("amount");
                        BigDecimal price = rs.getBigDecimal("price");
                        results.add(new OrderProduct(productId, amount, price));
                    }
                    return results;
                });
        return orderProducts;
    }

    public Order createOrder(long userId, List<OrderProduct> orderProducts, String message) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO `order`(user_id, message) VALUES(?, ?)",
                        new String[] { "id" });
                ps.setLong(1, userId);
                ps.setString(2, message);
                return ps;
            }
        }, keyHolder);
        long pk = keyHolder.getKey().longValue();

        for (OrderProduct orderProduct : orderProducts) {
            BigDecimal price = jdbcTemplate.queryForObject("SELECT price FROM product WHERE id = ?",
                    new Object[] { orderProduct.getId() }, BigDecimal.class);
            jdbcTemplate.update("INSERT INTO order_product(order_id, product_id, amount, price) VALUES(?, ?, ?, ?)", pk,
                    orderProduct.getId(), orderProduct.getAmount(), price);
        }
        return getOrder(userId, pk);
    }

    public void cancelOrder(long userId, long orderId) {
        jdbcTemplate.update("UPDATE `order` SET order_status = -1 WHERE user_id = ? AND id = ? AND payment_status = 0",
                new Object[] { userId, orderId });
    }

    public BigDecimal getOrderTotalPrice(long orderId) {
        BigDecimal price = jdbcTemplate.queryForObject(
                "SELECT SUM(price * amount) FROM order_product WHERE order_id = ?", new Object[] { orderId },
                BigDecimal.class);
        return price;
    }

    public boolean hasOrderProduct(long orderId, long productId) {
        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM order_product WHERE order_id = ? AND product_id = ?",
                new Object[] { orderId, productId }, Integer.class);
        return count >= 1;
    }

    public void updateOrder(long id, List<OrderProduct> orderProducts, int orderStatus, int paymentStatus,
            Timestamp createDate, String message, String adminMessage) {
        // Delete order product
        String ids = orderProducts.stream().map(cat -> String.valueOf(cat.getId())).collect(Collectors.joining(","));
        if (ids != null && ids.length() > 0)
            jdbcTemplate.update("DELETE FROM order_product WHERE product_id NOT IN( " + ids + ") AND order_id = ?",
                    new Object[] { id });
        else
            jdbcTemplate.update("DELETE FROM order_product WHERE order_id = ?", new Object[] { id });

        // Update amount
        for (OrderProduct orderProduct : orderProducts) {
            if (hasOrderProduct(id, orderProduct.getId())) {
                jdbcTemplate.update("UPDATE order_product SET amount = ? WHERE order_id = ? AND product_id = ?",
                        new Object[] { orderProduct.getAmount(), id, orderProduct.getId() });
            } else {
                BigDecimal price = BigDecimal.ZERO;
                price = jdbcTemplate.queryForObject("SELECT price FROM product WHERE id = ?",
                        new Object[] { orderProduct.getId() }, BigDecimal.class);

                jdbcTemplate.update(
                        "INSERT INTO order_product (order_id, product_id, amount, price) VALUES (?, ?, ?, ?)",
                        new Object[] { id, orderProduct.getId(), orderProduct.getAmount(), price });
            }
        }

        jdbcTemplate.update(
                "UPDATE `order` SET order_status = ?, payment_status = ?, create_date = ?, message = ?, admin_message = ? WHERE id = ?",
                new Object[] { orderStatus, paymentStatus, createDate, message, adminMessage, id });
        if (paymentStatus == 1) {
            jdbcTemplate.update("UPDATE `order` SET confirm_date = NOW() WHERE id = ? AND confirm_date IS NULL",
                    new Object[] { id });
        }
    }

    public int getNumberOfOrders(Date start, Date end) {
        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM `order` WHERE (create_date >= ? AND create_date < ?)",
                new Object[] { start, end }, Integer.class);
        return count;
    }

    public BigDecimal getSales(Date start, Date end) {
        BigDecimal sales = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(price * amount), 0) FROM `order_product` op JOIN `order` o ON o.id = op.order_id AND o.payment_status = 1 AND (o.confirm_date  >= ? AND o.confirm_date < ?)",
                new Object[] { start, end }, BigDecimal.class);
        return sales;
    }

    public long getTopSellingProduct(Date start, Date end) {
        try {
            long productId = jdbcTemplate.queryForObject(
                    "SELECT p.id FROM product p JOIN `order_product` op ON op.product_id = p.id JOIN `order` o ON o.id = op.order_id AND o.payment_status = 1 AND (o.confirm_date  >= ? AND o.confirm_date < ?) GROUP BY p.id, op.amount ORDER BY op.amount DESC LIMIT 0, 1",
                    new Object[] { start, end }, Long.class);
            return productId;
        } catch (Exception ex) {
            return 0l;
        }

    }
}
