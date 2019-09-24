package com.walter.AdminPagePractice.controller.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
//@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"orderGroupList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String email;

    private String phoneNumber;

    private String status; //Registered, Unregistered

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //관계설정 User 1 : N OrderGroup   mappedBy의 변수명은 OrderGroup에서의 User객체의 변수명이어야 한다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
