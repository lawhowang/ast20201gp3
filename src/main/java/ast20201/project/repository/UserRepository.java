package ast20201.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ast20201.project.domain.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addUser(User user) {
		jdbcTemplate.update("INSERT INTO User (username, password, email, phone, address, create_date)"
			+ "VALUES (?, ?, ?, ?, ?, NOW())", user.getUsername(), user.getHashedPassword(), user.getEmail(),
				user.getPhone(), user.getAddress());
	}
	
	public boolean checkUsernameDuplicated(String username) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM User WHERE username = ?", new Object[] { username }, Integer.class);
		return count >= 1;
	}
	
	public boolean checkEmailDuplicated(String email) {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM User WHERE email = ?", new Object[] { email }, Integer.class);
		return count >= 1;
	}

	public User getUserByUsernameOrEmail(String usernameOrEmail) throws EmptyResultDataAccessException {
		User user = jdbcTemplate.queryForObject("SELECT * FROM User WHERE username = ? OR email = ?", new Object[] { usernameOrEmail, usernameOrEmail }, new BeanPropertyRowMapper<User>(User.class));
		return user;
	}

	public User getUserById(Long userId) {
		User user = jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?", new Object[] { userId }, User.class);
		return user;
	}
}
