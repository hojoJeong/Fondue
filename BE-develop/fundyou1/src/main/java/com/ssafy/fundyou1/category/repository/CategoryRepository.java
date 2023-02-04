package com.ssafy.fundyou1.category.repository;

import com.ssafy.fundyou1.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);

//    Optional<Category> findByCategoryName(String categoryName);


    boolean existsByCategoryName(String categoryName);

    Category findByCategoryName(String categoryName);




}