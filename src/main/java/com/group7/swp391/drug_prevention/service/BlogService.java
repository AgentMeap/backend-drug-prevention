package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.Blog;
import com.group7.swp391.drug_prevention.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public List<Blog> searchBlogs(String searchTerm) {
        return blogRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm, searchTerm);
    }

    public List<Blog> getBlogsByType(String type) {
        return blogRepository.findByType(type);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}