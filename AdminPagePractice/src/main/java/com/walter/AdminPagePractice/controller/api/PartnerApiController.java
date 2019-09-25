package com.walter.AdminPagePractice.controller.api;

import com.walter.AdminPagePractice.controller.CrudController;
import com.walter.AdminPagePractice.controller.model.entity.Partner;
import com.walter.AdminPagePractice.controller.model.network.request.PartnerApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
