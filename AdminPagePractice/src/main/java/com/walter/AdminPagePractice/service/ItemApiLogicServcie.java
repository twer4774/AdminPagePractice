package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.Item;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.ItemApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.ItemApiResponse;
import com.walter.AdminPagePractice.controller.model.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemApiLogicServcie extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    //외래키이므로 autowired에 등록한다.
    @Autowired
    private PartnerRepository partnerRepository;
    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Item item = Item.builder()
                .name(itemApiRequest.getName())
                .status(itemApiRequest.getStatus())
                .content(itemApiRequest.getContent())
                .brandName(itemApiRequest.getBrandName())
                .price(itemApiRequest.getPrice())
                .partner(partnerRepository.getOne(itemApiRequest.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Optional<Item> optionalItem = baseRepository.findById(itemApiRequest.getId());

        return optionalItem.map( newItem -> {
                    newItem.setName(itemApiRequest.getName())
                            .setStatus(itemApiRequest.getStatus())
                            .setContent(itemApiRequest.getContent())
                            .setBrandName(itemApiRequest.getBrandName())
                            .setPrice(itemApiRequest.getPrice())
                            .setPrice(itemApiRequest.getPrice());

                    return newItem;
                }).map(newItem -> baseRepository.save(newItem))
                .map(newItem -> response(newItem))
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);

                    return Header.OK("OK");
                })
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemApiResponse> response(Item item){
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .content(item.getContent())
                .brandName(item.getBrandName())
                .status(item.getStatus())
                .price(item.getPrice())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(itemApiResponse);
    }
}
