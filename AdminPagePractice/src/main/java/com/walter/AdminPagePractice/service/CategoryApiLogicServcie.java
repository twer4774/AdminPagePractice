package com.walter.AdminPagePractice.service;

import com.walter.AdminPagePractice.controller.model.entity.Category;
import com.walter.AdminPagePractice.controller.model.network.Header;
import com.walter.AdminPagePractice.controller.model.network.request.CategoryApiRequest;
import com.walter.AdminPagePractice.controller.model.network.response.CategoryApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryApiLogicServcie extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();

        Category category = Category.builder()
                .name(categoryApiRequest.getName())
                .build();

        Category newCategory = baseRepository.save(category);

        return response(newCategory);

    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> requet) {
        CategoryApiRequest categoryApiRequest = requet.getData();

        Optional<Category> optionalCategroy = baseRepository.findById(categoryApiRequest.getId());

        return optionalCategroy.map(newCategory -> {
            newCategory.setName(categoryApiRequest.getName());

            return newCategory;
        })
                .map(newCategory -> baseRepository.save(newCategory))
                .map(newCategory -> response(newCategory))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(category -> {
                    baseRepository.delete(category);
                    return Header.OK("OK");
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<CategoryApiResponse> response(Category category){
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        return Header.OK(categoryApiResponse);
    }
}
