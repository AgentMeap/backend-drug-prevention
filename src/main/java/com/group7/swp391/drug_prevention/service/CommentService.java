package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Blog;
import com.group7.swp391.drug_prevention.domain.Comment;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqCommentDTO;
import com.group7.swp391.drug_prevention.repository.BlogRepository;
import com.group7.swp391.drug_prevention.repository.CommentRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private BlogRepository blogRepository;
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    public Comment addComment(ReqCommentDTO dto) {
        Comment comment = new Comment();
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        comment.setUser(user);
        Blog blog = blogRepository.findById(dto.getBlogId()).orElse(null);
        if (blog != null) {
            comment.setBlog(blog);
        }else{
            comment.setBlog(null);
        }

        comment.setDescription(dto.getDescription());
        comment.setCreatedAt(LocalTime.now());
        comment.setUpdatedAt(LocalTime.now());
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
