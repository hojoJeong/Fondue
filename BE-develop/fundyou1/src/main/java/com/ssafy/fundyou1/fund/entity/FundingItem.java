package com.ssafy.fundyou1.fund.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private int itemTotalPrice;

    private int count;

    // 현재 펀딩된 가격
    // 펀딩 참여자 DB 다 돌아서 sum 해준 값

    @JsonIgnore
    private boolean fundingItemStatus;

    // 참여자 수
    // 펀딩 참여자 DB 갯수 카운트 - @Query문 쓰면 되려나

    public static FundingItem createFundingItem(Funding funding, Item item, int count){
        FundingItem fundingItem = new FundingItem();
        fundingItem.setItem(item);
        fundingItem.setFunding(funding);
        // 아이템 total price
        fundingItem.setCount(count);
        fundingItem.setFundingItemStatus(Boolean.parseBoolean("True"));

        return fundingItem;
    }


}
