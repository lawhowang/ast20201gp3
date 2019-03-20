package ast20201.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.domain.User;
import ast20201.project.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.addUser(user);
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
}
