package ast20201.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ast20201.project.model.Comment;
import ast20201.project.model.PageData;
import ast20201.project.model.User;
import ast20201.project.service.CommentService;
import ast20201.project.service.UserService;

@RequestMapping(value = "/api/comments")
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getComments(@PathVariable("productId") long productId,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        PageData<Comment> comments = commentService.getComments(productId, page);
        return ResponseEntity.ok(comments);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> getComments(@PathVariable("productId") long productId, @RequestBody Comment comment) {
        User user = userService.getCurrentUser();
        commentService.addComment(user.getId(), productId, comment);
        PageData<Comment> comments = commentService.getComments(productId, 1);
        return ResponseEntity.ok(comments);
    }
}