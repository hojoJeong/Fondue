package com.ssafy.fundyou1.search.repository;

import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.search.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {

    @Query(value = "SELECT * " +
            "FROM search " +
            "ORDER BY search_count " +
            "DESC LIMIT 10", nativeQuery = true)
    List<Search> findSearchKeywordRankTop10();

    boolean existsByKeyword(String keyword);

    @Modifying
    @Query(value = "UPDATE search " +
            "SET search_count = search_count + 1 " +
            "WHERE search.keyword = :keyword", nativeQuery = true)
    void increaseSearchCount(String keyword);
}
