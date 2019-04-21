/*
 * Wrapper for containing particular pages data (Huge collection)
 */
package ast20201.project.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PageData<T> {
	private int currPage;
	private int maxPages;
	private List<T> items;
	private int totalCount;

	public PageData() {
		
	}

	public PageData(List<T> items, int currPage, int totalCount) {
		this.items = items;
        this.currPage = currPage;
        
        int maxItemsPerPage = 10;
		this.maxPages = (int) Math.ceil((double) totalCount / maxItemsPerPage);
		this.totalCount = totalCount;
	}

	public PageData(List<T> items, int currPage, int totalCount, int maxItemsPerPage) {
		this.items = items;
        this.currPage = currPage;
        
		this.maxPages = (int) Math.ceil((double) totalCount / maxItemsPerPage);
		this.totalCount = totalCount;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
