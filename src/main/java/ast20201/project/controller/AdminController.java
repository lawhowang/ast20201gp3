package ast20201.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ast20201.project.model.User;
import ast20201.project.payload.AllUsersResponse;
import ast20201.project.service.AdminService;

@RequestMapping(value = "/admin/api")
@Controller
public class AdminController {
	@Autowired
	AdminService adminService;

	final int maxItemsPerPage = 5;
	
	@RequestMapping(value = "/users/all-users", method = RequestMethod.GET)
	public ResponseEntity<?> allUsers(@RequestParam(value = "page", defaultValue = "1") int page, ModelMap model) {
		int totalUserCount = adminService.getTotalUserCount();
		int maxPages = (int) Math.ceil((double) totalUserCount / maxItemsPerPage);
		List<User> users = adminService.getUsersByPage(page, maxItemsPerPage);

		return ResponseEntity.ok(new AllUsersResponse(page, maxPages, users));
	}

	@RequestMapping(value = "/users/all-users/search", method = RequestMethod.GET)
	public ResponseEntity<?> usersListSearch(@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "page", defaultValue = "1") int page, ModelMap model) {
		List<User> usersAll = adminService.searchUsersByUsername(username);

		int totalUserCount = usersAll.size();
		int maxPages = (int) Math.ceil((double) totalUserCount / maxItemsPerPage);
		
		if (page > maxPages) page = maxPages;
		if (page < 1) page = 1;
		
		int start = (page - 1) * maxItemsPerPage;
		int end = start + maxItemsPerPage > totalUserCount - 1 ? totalUserCount : start + maxItemsPerPage;
		System.out.println(usersAll);
		System.out.println("start " + start + " end" + end);
		List<User> users = usersAll.subList(start, end);

		return ResponseEntity.ok(new AllUsersResponse(page, maxPages, users));
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestParam(value = "id") long id, ModelMap model) {
		User user = adminService.getUser(id);

		return ResponseEntity.ok(user);
	}
}
