package ast20201.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.User;
import ast20201.project.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	UserRepository userRepository;
	
	public int getTotalUserCount() {
		return userRepository.getTotalUserCount();
	}
	
	public List<User> getUsersByPage(int pageNo, int noOfUsersPerPage) {
		return userRepository.getUsersByPage(pageNo, noOfUsersPerPage);
	}
	
	public List<User> searchUsersByUsername(String username) {
		return userRepository.searchUsersByUsername(username);
	}
}
