package ast20201.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ast20201.project.model.User;
import ast20201.project.service.AdminService;

@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/admin")
@Controller
public class AdminController {
	@Autowired
	AdminService adminService;

	final int maxItemsPerPage = 5;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "WEB-INF/admin/index";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String signUp() {
		return "WEB-INF/admin/users";
	}

	@RequestMapping(value = "/users/user_list", method = RequestMethod.GET)
	public String usersList(@RequestParam(value = "page", defaultValue = "1") int page, ModelMap model) {
		int totalUserCount = adminService.getTotalUserCount();
		int maxPages = (int) Math.ceil((double) totalUserCount / maxItemsPerPage);
		System.out.println("m " + maxPages);
		List<User> users = adminService.getUsersByPage(page, maxItemsPerPage);
		model.put("searchMode", false);
		model.put("currPage", page);
		model.put("maxPages", maxPages);
		model.put("users", users);

		return "WEB-INF/admin/users/user_list";
	}

	@RequestMapping(value = "/users/user_list/search", method = RequestMethod.GET)
	public String usersListSearch(@RequestParam(value = "q", defaultValue = "") String username,
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
		
		model.put("searchMode", true);
		model.put("searchWord", username);
		model.put("currPage", page);
		model.put("maxPages", maxPages);
		model.put("users", users);

		return "WEB-INF/admin/users/user_list";
	}
}
