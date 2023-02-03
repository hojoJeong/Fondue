package com.ssafy.fundyou1.item.repository;

import com.ssafy.fundyou1.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {


    // 아이템 전체 조회
    @Override
    ArrayList<Item> findAll();

    // 카테고리별 아이템 불러오기
    @Query(value =
            "SELECT * " +
            "FROM item " +
            "WHERE category_id = :categoryId",
            nativeQuery = true)
    List<Item> findAllByCategoryId(@Param("categoryId") Long categoryId);


    // 랜덤 5개 상품 추출
    @Query(value = "SELECT * FROM item order by RAND() limit 5", nativeQuery = true)
    List<Item> findRandomItemById();


    // 조건에 맞는 상위 5개 상품 추출
    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE category_id = :categoryId " +
            "AND price BETWEEN :minPrice AND :maxPrice" +
            "ORDER BY selling_count LIMIT 5",
            nativeQuery = true)
    List<Item> findTopItem(Long categoryId, Long minPrice, Long maxPrice);


    List<Item> findOne(Long id);


    boolean existsByTitleAndBrand(String title, String brand);
}
