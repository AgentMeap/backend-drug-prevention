package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
