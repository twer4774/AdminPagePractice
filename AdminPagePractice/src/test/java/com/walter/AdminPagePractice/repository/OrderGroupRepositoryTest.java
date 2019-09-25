package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.model.entity.OrderGroup;
import com.walter.AdminPagePractice.controller.model.entity.User;
import com.walter.AdminPagePractice.controller.model.repository.OrderGroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;


    @Test
//    @Transactional
    public void create(){

        OrderGroup orderGroup = new OrderGroup();
        User user = new User();

        orderGroup.setStatus("Registered");
        orderGroup.setPaymentType("CARD");
        orderGroup.setRevAddress("서울 광진구");
        orderGroup.setRevName("길동");
        orderGroup.setRevPhoneNumber("010-1111-1111");
        orderGroup.setTotalPrice(BigDecimal.valueOf(3000000));
        orderGroup.setOrderAt(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("Admin");
        orderGroup.setUser(user.setId(1L));


        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
    }

    @Test
    @Transactional
    public void read(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(2L);
        orderGroup.ifPresent(readOrderGroup -> {
            System.out.println(readOrderGroup);
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(2L);

        //값이 있는지 확인 후 진행
        orderGroup.ifPresent(updateOrderGroup -> {
            updateOrderGroup.setStatus("UNREGISTERED");
            updateOrderGroup.setUpdatedAt(LocalDateTime.now());
            updateOrderGroup.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            orderGroupRepository.save(updateOrderGroup);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);

        orderGroup.ifPresent(deleteOrderGroup -> {
            orderGroupRepository.delete(deleteOrderGroup);
        });

        Optional<OrderGroup> deleteOrderDetail = orderGroupRepository.findById(1L);
        Assert.assertFalse(deleteOrderDetail.isPresent()); //false
    }
}
