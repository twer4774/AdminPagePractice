package com.walter.AdminPagePractice.controller.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiResponse {
    private Long id;

    private String status;

    private String revAddress;

    private String revName;

    private String revPhoneNumber;

    private String paymentType; //Card/Cash

    private BigDecimal totalPrice;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Long userId;

}
