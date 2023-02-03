package com.ssafy.fundyou1.category.service;

import com.ssafy.fundyou1.category.dto.CategorySaveRequest;
import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.category.repository.CategoryRepository;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.member.dto.request.MemberSaveRequest;
import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() ->  new BusinessException(ErrorCode.CATEGORY_NOT_FOUND_BY_ID));
    }


    @Transactional
    public String saveCategory(CategorySaveRequest request) {
        checkDuplicateCategoryName(request.getCategoryName());
        Category category = request.toCategory();
        return categoryRepository.save(category).getCategoryName();
    }

    public void checkDuplicateCategoryName(String name) {
        if(categoryRepository.existsByCategoryName(name)) {
            throw new BusinessException(ErrorCode.CATEGORY_CATEGORY_NAME_DUPLICATED);
        }
    }

}
