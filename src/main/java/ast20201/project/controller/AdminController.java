package ast20201.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ast20201.project.model.User;
import ast20201.project.model.AllUsers;
import ast20201.project.service.UserService;
import ast20201.project.validation.ValidationGroup;

@RequestMapping(value = "/admin/api")
@Controller
public class AdminController {
	@Autowired
	UserService userService;

	final int maxItemsPerPage = 10;

	@RequestMapping(value = "/users/all-users", method = RequestMethod.GET)
	public ResponseEntity<?> allUsers(@RequestParam(value = "page", defaultValue = "1") int page, ModelMap model) {
		int totalUserCount = userService.getTotalUserCount();
		int maxPages = (int) Math.ceil((double) totalUserCount / maxItemsPerPage);
		List<User> users = userService.getUsersByPage(page, maxItemsPerPage);

		return ResponseEntity.ok(new AllUsers(page, maxPages, users));
	}

	@RequestMapping(value = "/users/all-users/search", method = RequestMethod.GET)
	public ResponseEntity<?> usersListSearch(@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "page", defaultValue = "1") int page, ModelMap model) {
		List<User> usersAll = userService.searchUsersByUsername(username);

		int totalUserCount = usersAll.size();
		int maxPages = (int) Math.ceil((double) totalUserCount / maxItemsPerPage);

		if (page > maxPages)
			page = maxPages;
		if (page < 1)
			page = 1;

		int start = (page - 1) * maxItemsPerPage;
		int end = start + maxItemsPerPage > totalUserCount - 1 ? totalUserCount : start + maxItemsPerPage;
		System.out.println(usersAll);
		System.out.println("start " + start + " end" + end);
		List<User> users = usersAll.subList(start, end);

		return ResponseEntity.ok(new AllUsers(page, maxPages, users));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestParam(value = "id") long id, ModelMap model) {
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@Validated({ ValidationGroup.EditUser.class }) @RequestBody User user) {
		User dbuser = userService.getUser(user.getId());
		dbuser.setUsername(user.getUsername());
		if (null != user.getPassword() && user.getPassword().length() > 0) {
			String plainPassword = user.getPassword();
			String hashedPassword = DigestUtils.md5DigestAsHex(plainPassword.getBytes());
			dbuser.setPassword(hashedPassword);
		}
		dbuser.setEmail(user.getEmail());
		dbuser.setPhone(user.getPhone());
		dbuser.setAddress(user.getAddress());
		dbuser.setRole(user.getRole());
		userService.updateUser(dbuser);
		return ResponseEntity.ok(dbuser);
	}
}
