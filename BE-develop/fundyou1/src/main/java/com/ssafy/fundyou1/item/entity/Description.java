package com.ssafy.fundyou1.item.entity;

import com.ssafy.fundyou1.category.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String type;

    private String value;

    @Builder
    public Description(Item item, String type, String value ) {
        this.item = item;
        this.type = type;
        this.value = value;
    }


}
