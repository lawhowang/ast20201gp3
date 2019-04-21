/*
 * Comment model.
 */

package ast20201.project.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class Comment {
    private long productId;
    private long userId;
    private String username;
    private String comment;
    private int rating;
    private Timestamp date;

    public Comment() {

    }

    public Comment(long productId, long userId, String username, String comment, int rating, Timestamp date) {
        this.productId = productId;
        this.userId = userId;
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

    public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
    }
    
    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
    }
    
    public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
    }
    
    public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
