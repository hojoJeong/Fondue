package com.ssafy.fundyou1.member.repository;

import com.ssafy.fundyou1.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 영속성 컨텍스트에 멤버 정보를 넣는다.
    public void save(Member member) {
        em.persist(member);
    }

    //jpa find 메소드 사용 단권 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // jql을 씀 sql과 다른 점은 테이블이 아니라 엔터티를 씀
    public List<Member> findAll() {
        //엔티티 객체에 대한 쿼리문
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 조회 - 현재는 이름 없음!
//    public List<Member> findByName(String name) {
//        return em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
}
