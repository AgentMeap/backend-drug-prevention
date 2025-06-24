package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Blog;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqBlogDTO;
import com.group7.swp391.drug_prevention.repository.BlogRepository;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Service
public class BlogService {
    private BlogRepository blogRepository;
    private UserRepository userRepository;
    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog createBlog(ReqBlogDTO dto) {
        Blog blog = new Blog();
        blog.setContent(dto.getContent());
        blog.setTitle(dto.getTitle());
        blog.setCreatedAt(Instant.now());
        blog.setUpdatedAt(Instant.now());
        blog.setType(dto.getType());
        User manager = userRepository.findById(dto.getManagerId()).orElse(null);
        blog.setManager(manager);
        return blogRepository.save(blog);
    }

    public Blog getBlogById(long id) {
        return blogRepository.findById(id).orElse(null);
    }
}
