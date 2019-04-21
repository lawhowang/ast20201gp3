package ast20201.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ast20201.project.model.CartItem;

@Repository
public class CartRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CartItem> getItems(long userId) {
        List<CartItem> cartItems = jdbcTemplate.query("SELECT * FROM cart WHERE user_id = ?", new Object[] { userId },
                (ResultSet rs) -> {
                    List<CartItem> results = new ArrayList<CartItem>();
                    while (rs.next()) {
                        long productId = rs.getLong("product_id");
                        int amount = rs.getInt("amount");
                        results.add(new CartItem(productId, amount));
                    }
                    return results;
                });
        return cartItems;
    }

    public CartItem addItem(long userId, long productId, int amount) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO cart(user_id, product_id, amount, time) VALUES(?, ?, ?, NOW())",
                        new String[] { "id" });
                ps.setLong(1, userId);
                ps.setLong(2, productId);
                ps.setInt(3, amount);
                return ps;
            }
        }, keyHolder);
        long pk = keyHolder.getKey().longValue();
        CartItem cartItem = jdbcTemplate.queryForObject("SELECT * FROM cart WHERE id = ?", new Object[] { pk },
                (ResultSet rs, int row) -> {
                    long pid = rs.getLong("product_id");
                    int q = rs.getInt("amount");
                    return new CartItem(pid, q);
                });
        return cartItem;
    }

    public void updateItemAmount(long userId, long productId, int amount) {
        jdbcTemplate.update("UPDATE cart SET amount = ? WHERE user_id = ? AND product_id = ?",
                new Object[] { amount, userId, productId });
    }

    public int getItemAmount(long userId, long productId) {
        int amount = jdbcTemplate.queryForObject("SELECT IFNULL(SUM(amount), 0) AS amount FROM cart WHERE user_id = ? AND product_id = ?",
                new Object[] { userId, productId }, Integer.class);
        return amount;
    }

    public void removeItem(long userId, long productId) {
        jdbcTemplate.update("DELETE FROM cart WHERE user_id = ? AND product_id = ?", new Object[] { userId, productId });
    }

	public void clear(long userId) {
        jdbcTemplate.update("DELETE FROM cart WHERE user_id = ?", new Object[] { userId });
	}

}
