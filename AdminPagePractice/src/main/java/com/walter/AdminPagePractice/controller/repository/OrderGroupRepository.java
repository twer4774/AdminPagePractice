package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
    
}
