package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Comment;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqCommentDTO;
import com.group7.swp391.drug_prevention.domain.Blog;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import com.group7.swp391.drug_prevention.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Comment> getCommentsByBlogId(@RequestParam Long blog_id) {
        return commentService.getCommentsByBlogId(blog_id);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody ReqCommentDTO reqCommentDTO) {
        User user = userRepository.findById(reqCommentDTO.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Comment comment = new Comment();
        comment.setDescription(reqCommentDTO.getDescription());
        comment.setUser(user);
        comment.setBlog(new Blog());
        comment.getBlog().setId(reqCommentDTO.getBlogId());
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Comment> likeComment(@PathVariable Long id) {
        Comment comment = commentService.likeComment(id);
        return comment != null ? ResponseEntity.ok(comment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}