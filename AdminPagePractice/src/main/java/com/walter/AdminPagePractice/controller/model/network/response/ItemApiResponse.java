package com.walter.AdminPagePractice.controller.model.network.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiResponse {

    private Long id;

    private String name;

    private String status;

    private String content;

    private BigDecimal price;

    private String brandName;

    private Long partnerId;
}
