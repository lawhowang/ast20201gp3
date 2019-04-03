/*
 * Wrapper for containing several pages data (Searching results)
 */
package ast20201.project.model;

import java.util.List;

import ast20201.project.model.User;

public class PageableList<T> {
	private int currPage;
	private int maxPages;
	private List<T> items;

	public PageableList(List<T> items, int currPage) {
		this.items = items;
		this.currPage = currPage;

		int maxItemsPerPage = 10;
		int totalCount = items.size();
		this.maxPages = (int) Math.ceil((double) totalCount / maxItemsPerPage);

		if (this.currPage > this.maxPages)
			this.currPage = this.maxPages;
		if (this.currPage < 1)
			this.currPage = 1;

		int start = (this.currPage - 1) * maxItemsPerPage;
		int end = start + maxItemsPerPage > totalCount - 1 ? totalCount : start + maxItemsPerPage;
		this.items = this.items.subList(start, end);
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

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}
