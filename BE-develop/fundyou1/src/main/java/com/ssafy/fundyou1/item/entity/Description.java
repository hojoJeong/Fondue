package com.ssafy.fundyou1.item.entity;

import com.ssafy.fundyou1.category.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// 아이템 상세목록 엔티티
@Entity
@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자 추가
public class Description {

    @Id
    @GeneratedValue
    @Column(name = "description_id")
    private Long id;

    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id")
    private Item item;

    private String itemType;

    private String content;

    @Builder
    public Description(Item item, String itemType, String content ) {
        this.item = item;
        this.itemType = itemType;
        this.content = content;
    }
}
