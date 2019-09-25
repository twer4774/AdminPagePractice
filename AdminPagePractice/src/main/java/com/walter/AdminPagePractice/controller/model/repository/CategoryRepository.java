package com.walter.AdminPagePractice.controller.model.repository;

import com.walter.AdminPagePractice.controller.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
