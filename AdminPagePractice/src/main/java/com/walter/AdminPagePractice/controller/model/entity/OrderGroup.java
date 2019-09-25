package com.walter.AdminPagePractice.controller.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"user", "orderDetailList"}) //ToString으로 변환하는 작업에서 제외시킨다.
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String revAddress;

    private String revName;

    private String revPhoneNumber;

    private String paymentType; //Card/Cash

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    private String updatedBy;

    // 관계설정 User 1 : N OrderGroup
    @ManyToOne
    private User user;

    // 관계설정 OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
