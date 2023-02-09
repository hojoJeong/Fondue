package com.ssafy.fundyou1.ar.repository;

import com.ssafy.fundyou1.ar.entity.ArImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArRepository extends JpaRepository<ArImage, Long> {

    ArImage findByUrl(String url);

    @Query(value = "SELECT * FROM ar_image where funding_id = :funding_id and member_id = :member_id and item_id = :item_id", nativeQuery = true)
    List<ArImage> findArImageList(@Param("funding_id") Long funding_id,@Param("item_id") Long item_id,@Param("member_id") Long member_id);
}
