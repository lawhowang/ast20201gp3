package ast20201.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.User;
import ast20201.project.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void addUser(User user) {
		userRepository.addUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(),
				user.getAddress());
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

	public void updateLastLogin(Long userId) {
		userRepository.updateLastLogin(userId);
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
				dbuser.getPhone(), dbuser.getAddress(), dbuser.getRole());
	}
}
