package com.ssafy.fundyou1.like.entity;

import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.*;


// 찜 엔티티
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
@Entity @ToString
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // FK

    private Long item_id;

    @Builder
    public Like(Member member, Long item_id) {
        this.member = member;
        this.item_id = item_id;
    }

    // 찜 목록에 아이템을 등록한다.
    public static Like createLike(Member member, Long item_id) {
        Like like = new Like();
        like.setMember(member);
        like.setItem_id(item_id);
        return like;
    }

}
