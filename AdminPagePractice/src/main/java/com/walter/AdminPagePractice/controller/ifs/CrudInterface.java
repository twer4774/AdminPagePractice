package com.walter.AdminPagePractice.controller.ifs;

import com.walter.AdminPagePractice.controller.model.network.Header;
import org.springframework.stereotype.Component;

@Component
public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
