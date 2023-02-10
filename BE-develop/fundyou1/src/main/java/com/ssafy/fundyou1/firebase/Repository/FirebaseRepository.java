package com.ssafy.fundyou1.firebase.Repository;

import com.ssafy.fundyou1.firebase.entity.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirebaseRepository extends JpaRepository<FirebaseToken, Long> {
    Optional<FirebaseToken> findByMemberId(Long memberId);

    @Modifying
    @Query(value = "update firebase_token set firebase_token.target_token = :targetToken where firebase_token.member_id = :id", nativeQuery = true)
    void updateFirebase(@Param("id") Long id, @Param("targetToken") String targetToken);

}
