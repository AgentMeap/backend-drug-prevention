package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Comment;
import com.group7.swp391.drug_prevention.domain.request.ReqCommentDTO;
import com.group7.swp391.drug_prevention.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAllComment")
    public List<Comment> getComments() {
        return commentService.getAllComments();
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody ReqCommentDTO dto) {
        Comment comment = commentService.addComment(dto);
        if(comment.getBlog() == null){
            return new ResponseEntity<>("Blog doesn't exist!!",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
}
