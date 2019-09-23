package com.walter.AdminPagePractice.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
//@EntityListeners(AuditingEntityListener.class) //todo 반복적으로 입력되는 값 created_by 등을 자동입력할 때 쓰임
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status; //Registered, Unregistered

    private String role; //Admin, Developer

    private LocalDateTime lastLoginAt;

    private LocalDateTime passwordUpdateAt;

    private Integer loginFailCount;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
