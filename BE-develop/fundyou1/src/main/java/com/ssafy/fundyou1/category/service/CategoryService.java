package com.ssafy.fundyou1.category.service;

import com.ssafy.fundyou1.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Cate
}
