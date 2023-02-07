package com.ssafy.fundyou1.like.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.cart.entity.Cart;
import com.ssafy.fundyou1.item.entity.Item;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // FK


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id")
//    private Item item; // FK

    private Long item_id;


    @Builder
    public Like(Member member, Long item_id) {
        this.member = member;
        this.item_id = item_id;
    }

    // 찜 목록에 에 유저 할당하여 넣는다.
    public static Like createLike(Member member, Long item_id) {
        Like like = new Like();
        like.setMember(member);
        like.setItem_id(item_id);
        return like;
    }


}
