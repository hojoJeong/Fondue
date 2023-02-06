package com.ssafy.fundyou1.search.repository;

import com.ssafy.fundyou1.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends JpaRepository<Item, Long> {
    @Query(value =
            "SELECT * " +
                    "FROM item " +
                    "WHERE item.title LIKE %:keyword% " +
                    "AND item.price " +
                    "BETWEEN :minPrice AND :maxPrice",
            nativeQuery = true)
    List<Item> findBySearch(@Param("keyword")String keyword, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);
}
