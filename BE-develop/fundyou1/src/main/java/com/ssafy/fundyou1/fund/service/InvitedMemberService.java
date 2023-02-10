package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.fund.dto.InvitedFundingDto;
import com.ssafy.fundyou1.fund.dto.InvitedMemberDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
import com.ssafy.fundyou1.fund.repository.FundingItemMemberRepository;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.repository.InvitedMemberRepository;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class InvitedMemberService {
    private final FundingItemMemberRepository fundingItemMemberRepository;
    private final FundingItemRepository fundingItemRepository;
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
        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회


        // 펀딩 찾기
        Funding funding = fundingRepository.getById(invitedMemberDto.getFundingId());

        // 참여 멤버 기록 만들기
        InvitedMember invitedMember = InvitedMember.builder().member(member.get()).funding(funding).build();
        invitedMemberRepository.save(invitedMember);

        return invitedMember;

    }


    // 초대받은 펀딩 리스트 불러오기
    @Transactional
    public List<InvitedFundingDto> getInvitedFundingDtoList() {
        // 사용자 정보
        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회

        // 초대받은 펀딩 리스트 찾기
        List<InvitedMember> invitedFundingList = invitedMemberRepository.findAllByMemberId(member.get().getId());


        // 커스텀한 초대받은 펀딩 리스트
        List<InvitedFundingDto> invitedFundingDtoList = new ArrayList<>();


        for(InvitedMember invitedMember : invitedFundingList){
            // 초대 받은 펀딩
            Funding funding = fundingRepository.getById(invitedMember.getFunding().getId());

            // 초대 받은 펀딩 중 참여한 아이템 목록 리스트
            List<Long> fundingItemIdList = fundingItemRepository.findIdListByFundingId(funding.getId());

            // 해당 펀딩에서 지불한 총 금액 구하기
            int payTotalPoint = fundingItemMemberRepository.findAllByMemberIdAndFundingItemList(member.get().getId(), fundingItemIdList);

            // 펀딩에 내가 지불한 금액 커스텀 하기
            InvitedFundingDto invitedFundingDto = new InvitedFundingDto(funding, payTotalPoint);

            // 커스텀 값 리스트에 추가
            invitedFundingDtoList.add(invitedFundingDto);
        }

        return invitedFundingDtoList;

    }
}
