package ast20201.project.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ast20201.project.model.Category;

@Repository
public class CategoryRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Category> getCategories() {
		List<Category> categories = jdbcTemplate.query("SELECT * FROM category ORDER BY level, priority",
				(ResultSet rs) -> {
					Map<Long, Category> result = new LinkedHashMap<Long, Category>();
					while (rs.next()) {
						long id = rs.getLong("id");
						String name = rs.getString("name");
						long parentId = rs.getLong("parent_id");
						long order = rs.getLong("priority");
						long level = rs.getLong("level");
						if (parentId == 0) {
							result.put(id, new Category(id, name, order, level));
						} else {
							Category parent = result.get(parentId);
							Category subCategory = new Category(id, name, order, level);
							parent.addChildren(subCategory);
							result.put(id, subCategory); // Keep as an referece in the hash map for adding subcategories
						}
					}
					// Return level 0 category only
					List<Category> finalResult = new ArrayList<Category>();
					for (Map.Entry<Long, Category> r : result.entrySet()) {
						if (r.getValue().getLevel() == 0)
							finalResult.add(r.getValue());
					}
					return finalResult;
				});
		return categories;
	}

	public boolean hasCategory(long id) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM category WHERE id = ?", new Object[] { id },
				Integer.class);
		return count >= 1;
	}

	public void updateCategories(List<Category> categoryList) {
		updateCategories(0, categoryList, 0);
	}

	//	Recursive function
	private void updateCategories(long parentId, List<Category> categories, int level) {
		int priority = 0;
		for (Category subCategory : categories) {
			if (hasCategory(subCategory.getId())) {
				jdbcTemplate.update("UPDATE category SET name = ?, parent_id = ?, priority = ?, level = ? WHERE id = ?",
						subCategory.getName(), parentId, ++priority, level, subCategory.getId());
			} else {
				jdbcTemplate.update("INSERT INTO category (name, parent_id, priority, level) VALUES (?, ?, ?, ?)",
						subCategory.getName(), parentId, ++priority, level);
			}

			if (subCategory.getChildren().size() > 0)
				updateCategories(subCategory.getId(), subCategory.getChildren(), level + 1);
		}
	}

	public Category getCategory(long id) {
		Category category = jdbcTemplate.queryForObject("SELECT * FROM category WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Category>(Category.class));
		return category;
	}
}
