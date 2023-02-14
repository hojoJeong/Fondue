package com.ssafy.fundyou1.ar.repository;

import com.ssafy.fundyou1.ar.entity.ArImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ArImageRepository extends JpaRepository<ArImage, Long> {

    ArImage findByUrl(String url);

    @Query(value = "SELECT * FROM ar_image where funding_item_id = :fundingItemId", nativeQuery = true)
    List<ArImage> findArImageListByFundingItemId(@Param("fundingItemId") Long fundingItemId);

    @Query(value = "SELECT url FROM ar_image where funding_item_id = :fundingItemId", nativeQuery = true)
    List<String> findArUrlListByFundingItemId(@Param("fundingItemId") Long fundingItemId);
}
