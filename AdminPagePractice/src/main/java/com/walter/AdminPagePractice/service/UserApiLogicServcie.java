package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.User;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.UserApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.UserApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApiLogicServcie extends BaseService<UserApiRequest, UserApiResponse, User> {


    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .status(userApiRequest.getStatus())
                .build();

        User newUser = baseRepository.save(user);

        return response(user);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(readUser -> response(readUser))
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        Optional<User> optionalUser = baseRepository.findById(userApiRequest.getId());

        return optionalUser.map(updateUser -> {
            updateUser.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setEmail(userApiRequest.getEmail())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber());

            return updateUser;
        }).map(newUser -> baseRepository.save(newUser))
                .map(newUser -> response(newUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(deleteUser -> {
                    baseRepository.delete(deleteUser);
                    return Header.OK("OK");
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .status(user.getStatus())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return Header.OK(userApiResponse);
    }
}
