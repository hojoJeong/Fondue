package com.ssafy.fundyou1.ar.repository;

import com.ssafy.fundyou1.ar.entity.ArModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArModelRepository extends JpaRepository<ArModel, Long> {
    @Query(value = "SELECT * FROM ar_model WHERE item_id = :item_id", nativeQuery = true)
    ArModel findArModelByItemId(@Param("item_id") Long item_id);
}
