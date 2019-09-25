package com.walter.AdminPagePractice.controller.api;

import com.walter.AdminPagePractice.controller.CrudController;
import com.walter.AdminPagePractice.controller.model.entity.AdminUser;
import com.walter.AdminPagePractice.controller.model.network.request.AdminUserApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.AdminUserApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

}
