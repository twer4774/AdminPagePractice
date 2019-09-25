package com.walter.AdminPagePractice.controller;

import com.walter.AdminPagePractice.controller.ifs.CrudInterface;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

//Controller들의 부모 클래스 역할
@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) { return baseService.create(request); }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) { return baseService.read(id); }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) { return baseService.update(request); }

    @Override
    @DeleteMapping("{id}")
    public Header<Res> delete(@PathVariable Long id) { return baseService.delete(id); }
}
