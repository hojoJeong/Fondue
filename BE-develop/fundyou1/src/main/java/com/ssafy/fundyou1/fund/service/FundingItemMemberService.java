package com.ssafy.fundyou1.fund.service;


import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
import com.ssafy.fundyou1.fund.repository.FundingItemMemberRepository;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundingItemMemberService {
    @Autowired
    private FundingItemRepository fundingItemRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    FundingItemMemberRepository fundingItemMemberRepository;

    // 펀딩 참여--------------------------------------------------------------------------------------------------
    @Transactional
    public FundingItemMember attendFunding(FundingItem fundingItem) {
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩 참여
        FundingItemMember fundingItemMember = FundingItemMember.builder().fundingItem(fundingItem).member(member).build();
        fundingItemMemberRepository.save(fundingItemMember);

        return fundingItemMember;
    }


}
