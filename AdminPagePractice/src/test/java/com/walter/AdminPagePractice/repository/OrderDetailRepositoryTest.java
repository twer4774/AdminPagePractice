package com.walter.AdminPagePractice.repository;

import com.walter.AdminPagePractice.AdminPagePracticeApplicationTests;
import com.walter.AdminPagePractice.controller.model.entity.Item;
import com.walter.AdminPagePractice.controller.model.entity.OrderDetail;
import com.walter.AdminPagePractice.controller.model.entity.OrderGroup;
import com.walter.AdminPagePractice.controller.model.repository.OrderDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends AdminPagePracticeApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @Transactional
    public void create(){

        OrderDetail orderDetail = new OrderDetail();
        Item item = new Item();
        OrderGroup orderGroup = new OrderGroup();

        orderDetail.setStatus("Registered");
        orderDetail.setQuantity(2);
        orderDetail.setTotalPrice(BigDecimal.valueOf(20000000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("Admin");
        orderDetail.setItem(item.setId(1L));
        orderDetail.setOrderGroup(orderGroup.setId(1L));

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
    }

    @Test
    @Transactional
    public void read(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(2L);
        orderDetail.ifPresent(orderDetail1 -> {
            System.out.println(orderDetail);
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(2L);

        //값이 있는지 확인 후 진행
        orderDetail.ifPresent(updateOrderDetail -> {
            updateOrderDetail.setStatus("UNREGISTERED");
            updateOrderDetail.setUpdatedAt(LocalDateTime.now());
            updateOrderDetail.setUpdatedBy("AdminUser");

            //값 변경 후 저장
            orderDetailRepository.save(updateOrderDetail);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(2L);

        orderDetail.ifPresent(deleteOrderDetail -> {
            orderDetailRepository.delete(deleteOrderDetail);
        });

        Optional<OrderDetail> deleteOrderDetail = orderDetailRepository.findById(1L);
        Assert.assertFalse(deleteOrderDetail.isPresent()); //false
    }
}
