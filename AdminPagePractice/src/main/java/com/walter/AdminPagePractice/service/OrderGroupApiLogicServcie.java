package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.OrderGroup;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.OrderGroupApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.OrderGroupApiResponse;
import com.walter.AdminPagePractice.controller.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderGroupApiLogicServcie extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .paymentType(orderGroupApiRequest.getPaymentType())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .revPhoneNumber(orderGroupApiRequest.getRevPhoneNumber())
                .arrivalDate(orderGroupApiRequest.getArrivalDate())
                .orderAt(orderGroupApiRequest.getOrderAt())
                .status(orderGroupApiRequest.getStatus())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .user(userRepository.getOne(orderGroupApiRequest.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return response(orderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        Optional<OrderGroup> optionalOrderGroup = baseRepository.findById(orderGroupApiRequest.getId());

        return optionalOrderGroup.map( updateOrderGroup -> {
                    updateOrderGroup.setRevAddress(orderGroupApiRequest.getRevAddress())
                        .setArrivalDate(orderGroupApiRequest.getArrivalDate())
                        .setOrderAt(orderGroupApiRequest.getOrderAt())
                            .setStatus(orderGroupApiRequest.getStatus())
                            .setPaymentType(orderGroupApiRequest.getPaymentType())
                            .setRevName(orderGroupApiRequest.getRevName())
                            .setRevPhoneNumber(orderGroupApiRequest.getRevPhoneNumber())
                            .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                            .setUser(userRepository.getOne(orderGroupApiRequest.getUserId()));

                    return updateOrderGroup;
                })
                .map(newOrderGroup -> baseRepository.save(newOrderGroup))
                .map(newOrderGroup -> response(newOrderGroup))
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(deleteOrderGroup -> {
                    baseRepository.delete(deleteOrderGroup);
                    return Header.OK("OK");
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .paymentType(orderGroup.getPaymentType())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .revPhoneNumber(orderGroup.getRevPhoneNumber())
                .totalPrice(orderGroup.getTotalPrice())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(orderGroupApiResponse);
    }
}
