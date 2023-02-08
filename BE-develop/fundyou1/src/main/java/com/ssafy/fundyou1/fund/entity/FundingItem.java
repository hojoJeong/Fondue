package com.ssafy.fundyou1.fund.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.item.entity.Item;
import lombok.Builder;
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

    @ManyToOne(targetEntity = Item.class)
    @JsonIgnore
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(targetEntity = Funding.class)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    // 총 아이템 금액
    private int itemTotalPrice;

    private int count;


    // 현재 펀딩된 가격
    private int currentFundingPrice;

    private boolean fundingItemStatus;

    // 참여자 수--------------------------------------
    // 펀딩 참여자 DB 갯수 카운트 - @Query문 쓰면 되려나 or 펀딩 참여할 때 +1
    private int participantsCount;

    public static FundingItem createFundingItem(Funding funding, Item item, int count){
        FundingItem fundingItem = new FundingItem();
        fundingItem.setItem(item);
        fundingItem.setFunding(funding);
        fundingItem.setItemTotalPrice(count * item.getPrice());
        fundingItem.setCurrentFundingPrice(0);
        fundingItem.setCount(count);
        fundingItem.setFundingItemStatus(true);
        fundingItem.setParticipantsCount(0);

        return fundingItem;
    }


    // 펀딩 아이템 펀딩 종료
    public void changeFundingStatus(){
        this.fundingItemStatus = false;
    }


}
