package com.ssafy.fundyou1.fund.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FundingItemMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="funding_item_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="funding_item_id")
    @JsonIgnore
    private FundingItem fundingItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member;

    @Column(name = "funding_item_price")
    private int fundingItemPrice;

    @Column(name = "message")
    private String message;


    @Builder
    public FundingItemMember(Long id, FundingItem fundingItem, Member member, int fundingItemPrice, String message) {
        this.id = id;
        this.fundingItem = fundingItem;
        this.member = member;
        this.fundingItemPrice = fundingItemPrice;
        this.message = message;
    }

}
