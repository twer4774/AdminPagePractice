package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.model.entity.AdminUser;
import com.walter.AdminPagePractice.controller.model.repository.AdminUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class AdminUserRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    @Transactional
    public void create(){
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("TestAdminUser2");
        adminUser.setStatus("Registered");
        adminUser.setPassword("TestAdminUser2");
        adminUser.setRole("Admin");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("Admin");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);

        Assert.assertTrue(adminUser.isPresent());
        adminUser.ifPresent(admin-> {
            System.out.println(admin);
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);

        //값이 있는지 확인 후 진행
        adminUser.ifPresent(admin -> {
            admin.setAccount("TestAdminChange1");
            admin.setUpdatedAt(LocalDateTime.now());
            admin.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            adminUserRepository.save(admin);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(2L);

        adminUser.ifPresent(admin -> {
            adminUserRepository.delete(admin);
        });

        Optional<AdminUser> deleteAdmin = adminUserRepository.findById(2L);
        Assert.assertFalse(deleteAdmin.isPresent()); //false
    }
}
