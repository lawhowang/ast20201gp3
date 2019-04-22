package ast20201.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ast20201.project.model.UserWithProfile;

@Repository
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
