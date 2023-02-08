package com.ssafy.fundyou1.category.repository;

import com.ssafy.fundyou1.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);

    boolean existsByCategoryName(String categoryName);

    Category findByCategoryName(String categoryName);




}
