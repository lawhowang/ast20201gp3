package ast20201.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ast20201.project.model.Comment;
import ast20201.project.model.PageData;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CommentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PageData<Comment> getComments(long productId, int page) {
        int row = (page - 1) * 5;
        int offset = 5;
        List<Comment> comments = jdbcTemplate.query(
                "SELECT SQL_CALC_FOUND_ROWS user_id, username, comment, rating, date FROM product_comment JOIN user ON user.id = product_comment.user_id WHERE product_id = ? ORDER BY date DESC LIMIT ?, ?",
                new Object[] { productId, row, offset }, new BeanPropertyRowMapper<Comment>(Comment.class));
        int count = jdbcTemplate.queryForObject("SELECT FOUND_ROWS()", Integer.class);
        return new PageData<Comment>(comments, page, count, 5);
    }

    public void addComment(long userId, long productId, String comment, int rating) {
        jdbcTemplate.update("INSERT INTO product_comment (product_id, user_id, comment, rating) VALUES (?, ?, ?, ?)",
                new Object[] { productId, userId, comment, rating });
    }
}
