package com.ssafy.fundyou1.fund.repository;

import com.ssafy.fundyou1.fund.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {

    Optional<Funding> findById(Long fundingId);

    @Query(value = "select * from funding fi where fi.member_id = :memberId and fi.funding_status = :status", nativeQuery = true)
    List<Funding> findAllByMemberIdAndByFundingStatus(@Param("memberId") Long memberId, @Param("status") boolean status);

    // 펀딩 status 값 변경
    @Modifying(clearAutomatically = true)
    @Query(value = "update funding f set f.funding_status = :status where f.funding_id = :fundingId", nativeQuery = true)
    void updateStatus(@Param("fundingId") Long fundingId, @Param("status") boolean status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update funding f set f.funding_status = false where f.end_date < :nowDate", nativeQuery = true)
    void updateAllFundingStatus(@Param("nowDate") Long nowDate);

    @Query(value = "select * from funding where funding.end_date < :nowDate", nativeQuery = true)
    List<Funding> findNeedUpdateFunding(@Param("nowDate") Long nowDate);

    @Query(value = "select * from funding where funding.member_id = :memberId and funding.funding_status = true", nativeQuery = true)
    Funding findMyOngoingFunding(@Param("memberId") Long memberId);
}
