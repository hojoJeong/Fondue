package com.ssafy.fundyou1.firebase.Repository;

import com.ssafy.fundyou1.firebase.entity.Firebase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirebaseRepository extends JpaRepository<Firebase, Long> {
    Optional<Firebase> findByMemberId(Long memberId);

    @Query("update firebase set target_token = :targetToken")
    void updateFirebase(@Param("memberId") Long memberId, @Param("targetToken") String targetToken);

}
