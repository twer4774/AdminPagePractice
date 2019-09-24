package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.entity.Category;
import com.walter.AdminPagePractice.controller.entity.Partner;
import com.walter.AdminPagePractice.controller.repository.PartnerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class PartnerRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    @Transactional
    public void create(){
        Partner partner = new Partner();
        Category category = new Category();

        partner.setName("WalterC.O");
        partner.setAddress("서울시 광진구");
        partner.setStatus("Registered");
        partner.setBusinessNumber("010-1111-1111");
        partner.setCallCenter("070-0000-0010");
        partner.setCreatedAt(LocalDateTime.now());
        partner.setCreatedBy("Developer");
        partner.setCeoName("JO");
        partner.setCategory(category.setId(3L));

        Partner newPartner = partnerRepository.save(partner);
        Assert.assertNotNull(newPartner);
    }

    @Test
    @Transactional
    public void read(){
        Optional<Partner> partner = partnerRepository.findById(1L);

        Assert.assertTrue(partner.isPresent());
        partner.ifPresent(readPartner-> {
            System.out.println(readPartner);
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<Partner> user = partnerRepository.findById(1L);

        //값이 있는지 확인 후 진행
        user.ifPresent(updatePartner -> {
            updatePartner.setName("WalterCompany");
            updatePartner.setUpdatedAt(LocalDateTime.now());
            updatePartner.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            partnerRepository.save(updatePartner);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<Partner> partner = partnerRepository.findById(1L);

        partner.ifPresent(deletPartner -> {
            partnerRepository.delete(deletPartner);
        });

        Optional<Partner> deleteAdmin = partnerRepository.findById(1L);
        Assert.assertFalse(deleteAdmin.isPresent()); //false
    }

}
