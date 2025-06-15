package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.Blog;
import com.group7.swp391.drug_prevention.domain.request.ReqBlogDTO;
import com.group7.swp391.drug_prevention.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/getAllBlogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping("/addBlog")
    public ResponseEntity<?> addBlog(@RequestBody ReqBlogDTO dto) {
        blogService.createBlog(dto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/getBlogById/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }
}
