package ast20201.project.payload;

import java.util.List;

import ast20201.project.model.User;

public class AllUsersResponse {
	private int currPage;
	private int maxPages;
	private List<User> users;
	
	public AllUsersResponse(int page, int maxPages, List<User> users) {
		this.currPage = page;
		this.maxPages = maxPages;
		this.users = users;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
