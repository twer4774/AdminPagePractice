package com.walter.AdminPagePractice.controller.api;

import com.walter.AdminPagePractice.controller.CrudController;
import com.walter.AdminPagePractice.controller.model.entity.User;
import com.walter.AdminPagePractice.controller.model.network.request.UserApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.UserApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {
}
