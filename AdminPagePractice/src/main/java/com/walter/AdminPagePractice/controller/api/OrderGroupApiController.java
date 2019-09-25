package com.walter.AdminPagePractice.controller.api;

import com.walter.AdminPagePractice.controller.CrudController;
import com.walter.AdminPagePractice.controller.model.entity.OrderGroup;
import com.walter.AdminPagePractice.controller.model.network.request.OrderGroupApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
