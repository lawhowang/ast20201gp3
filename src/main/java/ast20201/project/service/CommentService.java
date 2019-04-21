package ast20201.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.Comment;
import ast20201.project.model.PageData;
import ast20201.project.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	
    public PageData<Comment> getComments(long productId, int page) {
        return commentRepository.getComments(productId, page);
    }

    public void addComment(long userId, long productId, Comment comment) {
        commentRepository.addComment(userId, productId, comment.getComment(), comment.getRating());
    }
}
