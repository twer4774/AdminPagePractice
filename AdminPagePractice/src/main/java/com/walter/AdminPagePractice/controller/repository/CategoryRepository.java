package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
