package com.walter.AdminPagePractice.controller.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"item", "orderGroup"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    private String updatedBy;

    //관계설정 item 1 : N orderDetail
    @ManyToOne
    private Item item;

    //관계설정 orderGroup 1 : N orderDetail
    @ManyToOne
    private OrderGroup orderGroup;
}
