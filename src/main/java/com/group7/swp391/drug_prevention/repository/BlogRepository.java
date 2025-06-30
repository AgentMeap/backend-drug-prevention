package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Blog> searchBlogs(@Param("searchTerm") String searchTerm);
    List<Blog> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
    List<Blog> findByType(String type);
}