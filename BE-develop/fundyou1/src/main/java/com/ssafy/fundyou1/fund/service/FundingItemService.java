package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.fund.dto.AttendFundingDto;
import com.ssafy.fundyou1.fund.dto.FundingItemDto;
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
public class FundingItemService {
    @Autowired
    private FundingItemMemberRepository fundingItemMemberRepository;
    @Autowired
    private FundingItemRepository fundingItemRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;


    // 펀딩 참여(돈 보내기)
    @Transactional
    public FundingItemDto attendFunding(AttendFundingDto attendFundingDto) {
        // 사용자 정보
        MemberResponseDto meDto = memberService.getMyInfo();
        Member member = memberRepository.findByUsername(meDto.getUsername());

        // 펀딩하려는 상품 찾기
        FundingItem fundingItem = fundingItemRepository.getReferenceById(attendFundingDto.getFundingItemId());
        fundingItem.getItem();
        fundingItem.getFunding();

        // 해당 펀딩에 참여했다는 데이터 기록 (fundingItem_member 테이블)
        FundingItemMember fundingItemMember = FundingItemMember.builder().fundingItem(fundingItem).member(member).fundingItemPrice(attendFundingDto.getPoint()).message(attendFundingDto.getMessage()).build();
        fundingItemMemberRepository.save(fundingItemMember);

        // 펀딩 상품에
        // 1. 펀딩 금액 추가
        fundingItemRepository.addCurrentFundingPrice(attendFundingDto.getFundingItemId(), attendFundingDto.getPoint());

        // 1-1.펀딩 금액 완료인지 확인 (=> 펀딩 완료시 => 펀딩 상태 False로 변경)
        if (fundingItem.getCurrentFundingPrice() == fundingItem.getItemTotalPrice()){
            fundingItemRepository.changeFundingStatus(fundingItem.getId());
        }

        // 2. 펀딩 참여자 수 + 1
        fundingItemRepository.addParticipantsCount(fundingItem.getId());


        // 4. 사용자 point 차감
        memberRepository.minusPoint(member.getUsername(), attendFundingDto.getPoint());



//        FundingItem updatedFundingItem = fundingItemRepository.getById(attendFundingDto.getFundingItemId());
//        FundingItemDto fundingItemDto = FundingItemDto.createFundingItemDto(fundingItemRepository.getById(attendFundingDto.getFundingItemId()));
        return getFundingItem(attendFundingDto.getFundingItemId());

    }


    public List<FundingItem> getInvitedFundingItemList(Long fundingId) {
        List<FundingItem> invitedFundingItemList = fundingItemRepository.findByFundingId(fundingId);
        return invitedFundingItemList;
    }

    public FundingItemDto getFundingItem(Long fundingItemId) {
        FundingItem fundingItem = fundingItemRepository.getReferenceById(fundingItemId);
        FundingItemDto fundingItemDto = FundingItemDto.createFundingItemDto(fundingItem);

        return fundingItemDto;
    }

    public List<FundingItemMember> getAttendMember(Long fundingItemId) {
        List<FundingItemMember> fundingItemMemberList = fundingItemMemberRepository.findAllByFundingItemId(fundingItemId);
        return fundingItemMemberList;
    }
}
