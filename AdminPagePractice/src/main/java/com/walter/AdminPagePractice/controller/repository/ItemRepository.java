package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
