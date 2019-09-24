package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.entity.Category;
import com.walter.AdminPagePractice.controller.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void create(){
        Category category= new Category();

        category.setName("전자제품");
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy("Developer");

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
    }

    @Test
    @Transactional
    public void read(){
        Optional<Category> category = categoryRepository.findById(3L);

        Assert.assertTrue(category.isPresent());
        category.ifPresent(readCategory-> {
            System.out.println(readCategory);
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<Category> user = categoryRepository.findById(1L);

        //값이 있는지 확인 후 진행
        user.ifPresent(updateCategory -> {
            updateCategory.setName("전자기기");
            updateCategory.setUpdatedAt(LocalDateTime.now());
            updateCategory.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            categoryRepository.save(updateCategory);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<Category> category = categoryRepository.findById(1L);

        category.ifPresent(deletCategory -> {
            categoryRepository.delete(deletCategory);
        });

        Optional<Category> deleteAdmin = categoryRepository.findById(1L);
        Assert.assertFalse(deleteAdmin.isPresent()); //false
    }

}
