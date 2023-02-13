package com.ssafy.fundyou1.item.repository;

import com.ssafy.fundyou1.item.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {

}
