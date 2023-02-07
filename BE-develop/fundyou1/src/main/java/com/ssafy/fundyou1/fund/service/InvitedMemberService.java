package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.fund.dto.InvitedMemberDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.repository.InvitedMemberRepository;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class InvitedMemberService {
    @Autowired
    private final FundingRepository fundingRepository;
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final InvitedMemberRepository invitedMemberRepository;

    // 초대 받은 펀딩 저장 (== 초대 된 Member 저장)
    @Transactional
    public InvitedMember storeInvitedFunding(InvitedMemberDto invitedMemberDto) {
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();
        // Dto => Entity
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩 찾기
        Funding funding = fundingRepository.getById(invitedMemberDto.getFundingId());

        // 참여 멤버 기록 만들기
        InvitedMember invitedMember = InvitedMember.builder().member(member).funding(funding).build();
        invitedMemberRepository.save(invitedMember);

        return invitedMember;

    }
}
