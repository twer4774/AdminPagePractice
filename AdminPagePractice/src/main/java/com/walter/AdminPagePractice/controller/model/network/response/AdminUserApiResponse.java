package com.walter.AdminPagePractice.controller.model.network.response;

import java.time.LocalDateTime;

public class AdminUserApiResponse {
    private Long id;

    private String account;

    private String password;

    private String status; //Registered, Unregistered

    private String role; //Admin, Developer

    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdateAt;

    private Integer loginFailCount;

    /* 아래의 4가지는 자동으로 입력되도록 설정 - entitiy에 @CreatedDate 어노테이션을 걸어준다.
    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
     */
}
