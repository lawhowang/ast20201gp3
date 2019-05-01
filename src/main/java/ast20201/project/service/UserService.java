package ast20201.project.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ast20201.project.model.User;
import ast20201.project.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			User user = (User) authentication.getPrincipal();
			User res = userRepository.getUser(user.getId());
			return res;
		} catch (Exception ex) {
			return null;
		}
	}

	public void addUser(User user) {
		user.setRole("user"); // Default role for sign up user
		userRepository.addUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
	}

	public User loginUser(User user) throws UsernameNotFoundException, BadCredentialsException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsernameOrEmail(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User dbUser = (User) authentication.getPrincipal();
		userRepository.updateLastLogin(dbUser.getId());
		return dbUser;
	}

	public void logoutUser() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public User getUserById(Long userId) {
		return userRepository.getUserById(userId);
	}

	public User getUserByUsernameOrEmail(String usernameOrEmail) {
		return userRepository.getUserByUsernameOrEmail(usernameOrEmail);
	}

	public boolean checkUsernameDuplicated(String username) {
		return userRepository.checkUsernameDuplicated(username);
	}

	public boolean checkEmailDuplicated(String email) {
		return userRepository.checkEmailDuplicated(email);
	}

	public int getTotalUserCount() {
		return userRepository.getTotalUserCount();
	}

	public List<User> getUsersByPage(int pageNo, int noOfUsersPerPage) {
		return userRepository.getUsersByPage(pageNo, noOfUsersPerPage);
	}

	public List<User> searchUsersByUsername(String username) {
		return userRepository.searchUsersByUsername(username);
	}

	public User getUser(long id) {
		return userRepository.getUser(id);
	}
	
	public void updateUser(User dbuser) {
		userRepository.updateUser(dbuser.getId(), dbuser.getUsername(), dbuser.getPassword(), dbuser.getEmail(),
				dbuser.getRole(), dbuser.getCredits());
	}

	public void deleteUser(long id) {
		userRepository.deleteUser(id);
	}

	public int getNumberOfUsers(Date start, Date end) {
		return userRepository.getNumberOfUsers(start, end);
	}
}
