package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
}
