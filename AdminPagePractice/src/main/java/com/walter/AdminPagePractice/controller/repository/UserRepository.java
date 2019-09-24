package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
