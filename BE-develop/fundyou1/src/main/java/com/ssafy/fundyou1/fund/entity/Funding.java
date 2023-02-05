package com.ssafy.fundyou1.fund.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="funding_id")
    private Long id; // PK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; // FK

    @CreatedDate
    @Column(name = "start_date")
    private LocalDateTime startDate; // due : 펀딩 시작 날짜

//    @Column(name = "end_date")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime endDate; // due : 펀딩 마감 날짜


    /**
     * orderStatus: 펀딩 상태(펀딩 진행중, 펀딩 마감 )
     */

    @Column(name = "funding_status")
    @ColumnDefault("True")
    private boolean fundingStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "funding",cascade = CascadeType.ALL)
    private List<FundingItem> fundingItems = new ArrayList<>(); // funding_order_item table의 fundingBaseOrder 의해 mapping

    //==연관 메서드 ==//

    public void setMember(Member member) {
        this.member = member;
        member.getFundings().add(this);
    }


    public void addFundingItem(FundingItem fundingItem) {
        fundingItems.add(fundingItem);
        fundingItem.setFunding(this);
    }


    @Builder
    public Funding(Long id, Member member, boolean fundingStatus) {
        this.id = id;
        this.member = member;
        this.fundingStatus = fundingStatus;
    }

    public static Funding createFunding(Member member){
        Funding funding = new Funding();
        funding.member = member;
        funding.fundingStatus = true;

        return funding;
    }



}
