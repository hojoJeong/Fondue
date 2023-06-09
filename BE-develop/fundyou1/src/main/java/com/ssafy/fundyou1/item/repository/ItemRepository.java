package com.ssafy.fundyou1.item.repository;

import com.ssafy.fundyou1.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    // 아이템 전체 조회 리스트
    List<Item> findAll();

    // 카테고리별 아이템 불러오기
    @Query(value =
            "SELECT * " +
            "FROM item " +
            "WHERE item.category_id = :categoryId",
            nativeQuery = true)
    List<Item> findAllByCategoryId(@Param("categoryId") Long categoryId);


    // 랜덤 6개 상품 추출
    @Query(value = "SELECT * FROM item order by RAND() limit 6", nativeQuery = true)
    List<Item> findSixRandomItem();


    // 조건에 맞는 상위 6개 상품 추출
    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE item.category_id = :categoryId " +
                    "AND item.price BETWEEN :minPrice AND :maxPrice " +
                    "ORDER BY item.selling_count DESC LIMIT 6",
            nativeQuery = true)
    List<Item> findTopItem(@Param("categoryId") Long categoryId, @Param("minPrice") Long minimumPrice,@Param("maxPrice") Long maxPrice);

    boolean existsByTitleAndBrand(String title, String brand);


    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE item.title LIKE %:keyword% " +
                    "AND item.price " +
                    "BETWEEN :minPrice AND :maxPrice",
            nativeQuery = true)
    List<Item> findBySearch(@Param("keyword")String keyword, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

    Optional<Item> findById(Long itemId);


    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE item.category_id = :categoryId " +
                    "AND item.price BETWEEN :minPrice AND :maxPrice ",
            nativeQuery = true)
    List<Item> findItemWithFilter(@Param("categoryId")  Long categoryId, @Param("minPrice") Long minPrice, @Param("maxPrice") Long maxPrice);

    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE item.price BETWEEN :minPrice AND :maxPrice " +
                    "ORDER BY item.selling_count DESC LIMIT 5",
            nativeQuery = true)
    List<Item> findTopItemNoCategory(@Param("minPrice") Long minimumPrice,@Param("maxPrice") Long maxPrice);

    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE item.price BETWEEN :minPrice AND :maxPrice ",
            nativeQuery = true)
    List<Item> findItemWithFilterNoCategory(@Param("minPrice") Long minPrice, @Param("maxPrice") Long maxPrice);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update item set selling_count = selling_count + 1 where item_id = :itemId", nativeQuery = true)
    void updateCountPlus(@Param("itemId") Long itemId);
}
