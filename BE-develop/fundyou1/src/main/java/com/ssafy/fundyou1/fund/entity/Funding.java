package com.ssafy.fundyou1.fund.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="funding_id")
    private Long id; // PK
    @Column(name = "funding_name")
    private String fundingName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member; // FK

    @Column(name = "start_date")
    private Long startDate; // due : 펀딩 시작 날짜

    @Column(name = "end_date")
    private Long endDate; // due : 펀딩 마감 날짜

    @Column(name = "funding_status")
    private boolean fundingStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "funding",cascade = CascadeType.ALL)
    private List<FundingItem> fundingItems = new ArrayList<>(); // funding_order_item table의 fundingBaseOrder 의해 mapping

    //==연관 메서드 ==//


    @Builder
    public Funding(Long id, Member member, Long startDate, Long endDate) {
        this.id = id;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fundingStatus = true;
    }


    public static Funding createFunding(String fundingName, Member member, Long startDate, Long endDate){
        Funding funding = new Funding();
        funding.fundingName = fundingName;
        funding.member = member;
        funding.startDate = startDate;
        funding.endDate = endDate;
        funding.fundingStatus = true;

        return funding;
    }



}
