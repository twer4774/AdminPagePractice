package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.model.entity.Item;
import com.walter.AdminPagePractice.controller.model.entity.Partner;
import com.walter.AdminPagePractice.controller.model.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
//    @Transactional
    public void create(){
        Item item = new Item();
        Partner partner = new Partner();

        item.setStatus("REGISTERED");
        item.setPrice(BigDecimal.valueOf(700000));
        item.setName("노트북");
        item.setContent("노트북");
        item.setBrandName("walter");
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("AdminUser");
        item.setPartner(partner.setId(1L));

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    @Transactional
    public void read(){
        Optional<Item> item = itemRepository.findById(1L);

        Assert.assertTrue(item.isPresent());
        item.ifPresent(readItem -> {
            System.out.println(readItem);
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<Item> item = itemRepository.findById(1L);

        //값이 있는지 확인 후 진행
        item.ifPresent(updateItem -> {
            updateItem.setStatus("UNREGISTERED");
            updateItem.setBrandName("WalterCompany");
            updateItem.setUpdatedAt(LocalDateTime.now());
            updateItem.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            itemRepository.save(updateItem);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<Item> item = itemRepository.findById(1L);

        item.ifPresent(deleteItem -> {
            itemRepository.delete(deleteItem);
        });

        Optional<Item> deleteItem = itemRepository.findById(1L);
        Assert.assertFalse(deleteItem.isPresent()); //false
    }
}
