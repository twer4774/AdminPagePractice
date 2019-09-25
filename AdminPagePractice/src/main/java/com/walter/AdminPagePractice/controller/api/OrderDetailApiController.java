package com.walter.AdminPagePractice.controller.api;

import com.walter.AdminPagePractice.controller.CrudController;
import com.walter.AdminPagePractice.controller.model.entity.OrderDetail;
import com.walter.AdminPagePractice.controller.model.network.request.OrderDetailApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.OrderDetailApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

}
