package com.ssafy.fundyou1.fund.entity;

import com.ssafy.fundyou1.category.entity.Category;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FundingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="funding_item_id")
    private Long id; // PK

    @ManyToOne(targetEntity = Item.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(targetEntity = Funding.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    private int fundingPrice;

    private int count;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


}
