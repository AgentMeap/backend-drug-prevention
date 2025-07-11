package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Blog;
import com.group7.swp391.drug_prevention.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import com.group7.swp391.drug_prevention.domain.request.ReqBlogDTO;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        return blog != null ? ResponseEntity.ok(blog) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Blog> searchBlogs(@RequestParam String term) {
        return blogService.searchBlogs(term);
    }

    @GetMapping("/type/{type}")
    public List<Blog> getBlogsByType(@PathVariable String type) {
        return blogService.getBlogsByType(type);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<Blog> createBlog(@RequestBody ReqBlogDTO reqBlogDTO) {
        User manager = userRepository.findById(reqBlogDTO.getManagerId()).orElse(null);
        if (manager == null) {
            return ResponseEntity.badRequest().build();
        }
        Blog blog = new Blog();
        blog.setTitle(reqBlogDTO.getTitle());
        blog.setContent(reqBlogDTO.getContent());
        blog.setType(reqBlogDTO.getType());
        blog.setManager(manager);
        blog.setImageUrl(reqBlogDTO.getImageUrl());
        Blog savedBlog = blogService.saveBlog(blog);
        return ResponseEntity.ok(savedBlog);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody ReqBlogDTO reqBlogDTO) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return ResponseEntity.notFound().build();
        }
        User manager = userRepository.findById(reqBlogDTO.getManagerId()).orElse(null);
        if (manager == null) {
            return ResponseEntity.badRequest().build();
        }
        blog.setTitle(reqBlogDTO.getTitle());
        blog.setContent(reqBlogDTO.getContent());
        blog.setType(reqBlogDTO.getType());
        blog.setManager(manager);
        blog.setImageUrl(reqBlogDTO.getImageUrl());
        Blog updatedBlog = blogService.saveBlog(blog);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}