package com.walter.AdminPagePractice.controller.repository;

import com.walter.AdminPagePractice.controller.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    
}
