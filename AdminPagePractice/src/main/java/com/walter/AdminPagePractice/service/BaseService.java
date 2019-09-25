package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.ifs.CrudInterface;
import com.walter.AdminPagePractice.controller.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    //Entity를 입력 받아서 Entity에 해당하는 Repsitory에 자동 연결한다.
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
}
