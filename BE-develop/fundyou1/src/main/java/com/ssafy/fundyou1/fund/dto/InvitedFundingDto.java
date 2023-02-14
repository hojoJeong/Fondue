package com.ssafy.fundyou1.fund.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Setter
public class InvitedFundingDto {

    private Long id;

    private String userName;
    private String profileImg;
    private Long startDate; // due : 펀딩 시작 날짜

    private Long endDate; // due : 펀딩 마감 날짜
    private boolean fundingStatus;
    private int fundingPoint;   // 내가 펀딩한 금액


    public InvitedFundingDto(Funding funding, int point){
        this.id = funding.getId();
        this.userName = funding.getMember().getUsername();
        this.profileImg = funding.getMember().getProfileImg();
        this.startDate = funding.getStartDate();
        this.endDate = funding.getEndDate();
        this.fundingStatus = funding.isFundingStatus();
        this.fundingPoint = point;

    }

}
