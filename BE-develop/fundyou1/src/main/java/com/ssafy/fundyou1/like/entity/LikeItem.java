package com.ssafy.fundyou1.like.entity;


import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class LikeItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "like_item_id")
    private Long id;

    @ManyToOne(targetEntity = Item.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(targetEntity = Like.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "likes_id")
    private Like like;

//    @Column
//    private int productCnt;



}
