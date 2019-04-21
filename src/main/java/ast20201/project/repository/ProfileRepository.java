package ast20201.project.repository;

import java.math.BigDecimal;
import java.sql.Connection;
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
import org.springframework.transaction.annotation.Transactional;

import ast20201.project.model.Order;
import ast20201.project.model.OrderProduct;
import ast20201.project.model.PageData;
import ast20201.project.model.UserWithProfile;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProfileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createIfProfileNotExists(long userId) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_profile WHERE user_id = ?", new Object[] { userId }, Integer.class);
		if (count == 0) {
            jdbcTemplate.update("INSERT INTO user_profile (user_id) VALUES (?)", new Object[] { userId });
        }
	}

    public UserWithProfile getProflie(long userId) {
        createIfProfileNotExists(userId);
        UserWithProfile userWithProfile = jdbcTemplate.queryForObject(
                "SELECT user.id, username, password, email, role, create_date, last_login_date, credits, firstName, lastName, gender, phone, address FROM `user` LEFT JOIN user_profile ON user.id = user_profile.user_id WHERE user.id = ?",
                new Object[] { userId }, new BeanPropertyRowMapper<UserWithProfile>(UserWithProfile.class));
        return userWithProfile;
    }

	public void updateProfile(long userId, UserWithProfile userWithProfile) {
        createIfProfileNotExists(userId);
        String email = userWithProfile.getEmail();
        String firstName  = userWithProfile.getFirstName();
        String lastName = userWithProfile.getLastName();
        String gender = userWithProfile.getGender();
        String phone = userWithProfile.getPhone();
        String address = userWithProfile.getAddress();
        jdbcTemplate.update("UPDATE user_profile SET firstName = ?, lastName = ?, gender = ?, phone = ?, address = ? WHERE user_id = ?",
        new Object[] { firstName, lastName, gender, phone, address, userId });
        jdbcTemplate.update("UPDATE user SET email = ? WHERE id = ?",
        new Object[] { email, userId });
	}
}
