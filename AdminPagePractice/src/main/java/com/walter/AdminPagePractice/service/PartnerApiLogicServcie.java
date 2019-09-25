package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.Partner;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.PartnerApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.PartnerApiResponse;
import com.walter.AdminPagePractice.controller.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerApiLogicServcie extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        Partner partner = Partner.builder()
                .status(partnerApiRequest.getStatus())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .address(partnerApiRequest.getAddress())
                .name(partnerApiRequest.getName())
                .category(categoryRepository.getOne(partnerApiRequest.getCategoryId()))
                .build();

        Partner newPartner = baseRepository.save(partner);

        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(readPartner -> response(readPartner))
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        Optional<Partner> optionalPartner = baseRepository.findById(partnerApiRequest.getId());

        return optionalPartner.map( updatePartner -> {
            updatePartner.setName(partnerApiRequest.getName())
                    .setAddress(partnerApiRequest.getAddress())
                    .setBusinessNumber(partnerApiRequest.getBusinessNumber())
                    .setCeoName(partnerApiRequest.getCeoName())
                    .setCallCenter(partnerApiRequest.getCallCenter())
                    .setStatus(partnerApiRequest.getStatus())
                    .setCategory(categoryRepository.getOne(partnerApiRequest.getCategoryId()));

            return updatePartner;
        })
                .map(newPartner -> baseRepository.save(newPartner))
                .map(newPartner -> response(newPartner))
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(deletePartner -> {
                    baseRepository.delete(deletePartner);
                    return Header.OK("OK");
                })
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .status(partner.getStatus())
                .name(partner.getName())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .categoryId(partner.getCategory().getId())
                .build();
        return Header.OK(partnerApiResponse);
    }
}
