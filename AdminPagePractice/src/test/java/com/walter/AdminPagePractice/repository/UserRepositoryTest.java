package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.entity.User;
import com.walter.AdminPagePractice.controller.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    @Transactional
    public void create(){
        User user = new User();

        user.setAccount("TestUser1");
        user.setPassword("TestUser1");
        user.setStatus("Registered");
        user.setEmail("testUser@gmail.com");
        user.setPhoneNumber("010-111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("AdminUser");

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        Assert.assertTrue(user.isPresent());
        user.ifPresent(readUser-> {
            System.out.println(readUser);
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        //값이 있는지 확인 후 진행
        user.ifPresent(updateUser -> {
            updateUser.setAccount("TestAdminChange1");
            updateUser.setUpdatedAt(LocalDateTime.now());
            updateUser.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            userRepository.save(updateUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(deletUser -> {
            userRepository.delete(deletUser);
        });

        Optional<User> deleteAdmin = userRepository.findById(1L);
        Assert.assertFalse(deleteAdmin.isPresent()); //false
    }

}
