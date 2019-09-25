package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.OrderDetail;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.OrderDetailApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.OrderDetailApiResponse;
import com.walter.AdminPagePractice.controller.model.repository.ItemRepository;
import com.walter.AdminPagePractice.controller.model.repository.OrderGroupRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailApiLogicServcie extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(orderDetailApiRequest.getStatus())
                .quantity(orderDetailApiRequest.getQuantity())
                .arrivalDate(orderDetailApiRequest.getArrivalDate())
                .totalPrice(orderDetailApiRequest.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
                .item(itemRepository.getOne(orderDetailApiRequest.getItemId()))
                .build();

        OrderDetail newOrderDtail = baseRepository.save(orderDetail);

        return response(newOrderDtail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderDeatail -> response(orderDeatail))
                .orElseGet( () -> Header.ERROR("데이터 없"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        Optional<OrderDetail> optionalOrderDetail = baseRepository.findById(orderDetailApiRequest.getId());

        return optionalOrderDetail.map(newOrderDetail -> {
            newOrderDetail.setStatus(orderDetailApiRequest.getStatus())
                    .setQuantity(orderDetailApiRequest.getQuantity())
                    .setArrivalDate(orderDetailApiRequest.getArrivalDate())
                    .setTotalPrice(orderDetailApiRequest.getTotalPrice())
                    .setOrderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
                    .setItem(itemRepository.getOne(orderDetailApiRequest.getItemId()));

            return newOrderDetail;
        }).map(newOrderDetail -> baseRepository.save(newOrderDetail))
                .map(newOrderDetail -> response(newOrderDetail))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(deleteOrderDetail -> {
                    baseRepository.delete(deleteOrderDetail);

                    return Header.OK("OK");
                })
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail){
        OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .arrivalDate(orderDetail.getArrivalDate())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();

        return Header.OK(orderDetailApiResponse);
    }
}
