package com.walter.AdminPagePractice.controller;

import com.walter.AdminPagePractice.controller.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Controller들의 부모 클래스 역할
@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {


}
