package com.walter.AdminPagePractice.controller.model.repository;

import com.walter.AdminPagePractice.controller.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
