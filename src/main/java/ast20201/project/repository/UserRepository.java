package ast20201.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import ast20201.project.model.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addUser(User user) {
		jdbcTemplate.update(
				"INSERT INTO User (username, password, email, phone, address, role, create_date)"
						+ "VALUES (?, ?, ?, ?, ?, 'user', NOW())",
				user.getUsername(), user.getHashedPassword(), user.getEmail(), user.getPhone(), user.getAddress());
	}

	public boolean checkUsernameDuplicated(String username) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM User WHERE username = ?",
				new Object[] { username }, Integer.class);
		return count >= 1;
	}

	public boolean checkEmailDuplicated(String email) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM User WHERE email = ?", new Object[] { email },
				Integer.class);
		return count >= 1;
	}

	public User getUserByUsernameOrEmail(String usernameOrEmail) throws EmptyResultDataAccessException {
		User user = jdbcTemplate.queryForObject("SELECT * FROM User WHERE username = ? OR email = ?",
				new Object[] { usernameOrEmail, usernameOrEmail }, new BeanPropertyRowMapper<User>(User.class));
		return user;
	}

	public User getUserById(Long userId) {
		User user = jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?", new Object[] { userId },
				new BeanPropertyRowMapper<User>(User.class));
		return user;
	}

	public void updateLastLogin(Long userId) {
		jdbcTemplate.update("UPDATE User SET last_login_date = NOW() WHERE id = ?", new Object[] { userId });
	}

	public List<User> getUsersByPage(int pageNo, int noOfUsersPerPage) {
		int row = (pageNo - 1) * noOfUsersPerPage;
		int offset = noOfUsersPerPage;
		List<User> users = jdbcTemplate.query("SELECT * FROM user LIMIT ?,?", new Object[] { row, offset },
				new BeanPropertyRowMapper<User>(User.class));
		return users;
	}
	
	public List<User> searchUsersByUsername(String username) {
		List<User> users = jdbcTemplate.query("SELECT * FROM user WHERE username LIKE ?", new Object[] { "%" + username + "%" },
				new BeanPropertyRowMapper<User>(User.class));
		return users;
	}

	public int getTotalUserCount() {
		int count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM user",
				Integer.class);
		return count;
	}

	public User getUser(long id) {
		return getUserById(id);
	}
}