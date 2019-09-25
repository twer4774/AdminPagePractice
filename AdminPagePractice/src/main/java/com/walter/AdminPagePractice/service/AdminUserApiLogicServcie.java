package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.AdminUser;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.AdminUserApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.AdminUserApiResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminUserApiLogicServcie extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser>{


    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        //1. 입력되는 request로 부터 데이터를 가져온다.
        AdminUserApiRequest adminUserApiRequest = request.getData();

        //2. AdminUser의 생성자를 초기화 한다.(builder 이용)
        AdminUser adminUser = AdminUser.builder()
                .account(adminUserApiRequest.getAccount())
                .password(adminUserApiRequest.getPassword())
                .role(adminUserApiRequest.getRole())
                .status(adminUserApiRequest.getStatus())
                .lastLoginAt(adminUserApiRequest.getLastLoginAt())
                .loginFailCount(adminUserApiRequest.getLoginFailCount())
                .passwordUpdateAt(adminUserApiRequest.getPasswordUpdateAt())
                .build();

        //3. 새로운 AdminUser객체에 저장한다.
        AdminUser newAdminUser = baseRepository.save(adminUser);

        //4. 공통으로 이용되는 response메소드를 통해 값을 반환한다.
        return response(newAdminUser);

    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        //1. id 값으로 baseRepository를 이용해 데이터를 찾는다.
        //2. 찾은 객체를 response메소드로 매핑하여 반환한다.
        return baseRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음")); //3. 실패하는 케이스에 대해 Error를 추가한다.
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        //1. AdminUserApiRequest의 객체로 입력파라미터의 request의 데이터를 가져온다.
        AdminUserApiRequest adminUserApiRequest = request.getData();

        //2. AdminUserApiRequest 객체에서 id를 가져오고, baseRepository를 이용해 데이터를 가져온다.
        return baseRepository.findById(adminUserApiRequest.getId())
                .map(adminUser -> {
                    //3. setter를 이용해 객체를 초기화 시킨다.
                    adminUser.setRole(adminUserApiRequest.getRole())
                            .setAccount(adminUserApiRequest.getRole())
                            .setPassword(adminUserApiRequest.getPassword())
                            .setLastLoginAt(adminUserApiRequest.getLastLoginAt())
                            .setLoginFailCount(adminUserApiRequest.getLoginFailCount())
                            .setStatus(adminUserApiRequest.getStatus())
                            .setPasswordUpdateAt(adminUserApiRequest.getPasswordUpdateAt());

                    return adminUser;
                })
                .map(newAdminUser -> baseRepository.save(newAdminUser))  //4. baseRepository를 이용해 새로운 객체를 저장한다.
                .map(newAdminUser -> response(newAdminUser))    //5. response메소드를 이용해 반환한다.
                .orElseGet(() -> Header.ERROR("데이터 없음"));   //6. 실패하는 케이스에 대해 Error를 추가한다.
    }

    @Override
    public Header delete(Long id) {
        //1. id값으로 baseRepository이용해 데이터를 찾는다.
        //2. baseRepository에서 데이터를 삭제한다.
        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);

                    return Header.OK("OK");
                })
                .orElseGet(() -> Header.ERROR("데이터 없음")); //3. 실패하는 케이스에 대해 Error를 추가한다.
    }

    //공통으로 이용되는 response method
    private Header<AdminUserApiResponse> response(AdminUser adminUser){
        //1. 입력되는 adminUser객체를 이용해 AdminUserApiResponse의 객체를 초기화한다.
        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .role(adminUser.getRole())
                .status(adminUser.getStatus())
                .lastLoginAt(adminUser.getLastLoginAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .passwordUpdateAt(adminUser.getPasswordUpdateAt())
                .build();
        //2. OK 결과코드와 함께 반환한다.
        return Header.OK(adminUserApiResponse);
    }
}
