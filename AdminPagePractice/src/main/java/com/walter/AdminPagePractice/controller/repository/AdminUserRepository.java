package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

}
