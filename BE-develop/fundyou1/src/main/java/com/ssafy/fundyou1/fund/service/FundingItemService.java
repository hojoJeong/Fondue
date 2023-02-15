package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.ar.repository.ArImageRepository;
import com.ssafy.fundyou1.firebase.FirebaseCloudMessageService;
import com.ssafy.fundyou1.fund.dto.AttendFundingDto;
import com.ssafy.fundyou1.fund.dto.FundingItemAttendedMemberResponseDto;
import com.ssafy.fundyou1.fund.dto.FundingItemResponseDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.repository.FundingItemMemberRepository;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.repository.InvitedMemberRepository;
import com.ssafy.fundyou1.global.security.SecurityUtil;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundingItemService {
    private final ArImageRepository arImageRepository;
    private final FundingRepository fundingRepository;
    @Autowired
    InvitedMemberRepository invitedMemberRepository;
    @Autowired
    FundingItemMemberRepository fundingItemMemberRepository;
    @Autowired
    FundingItemRepository fundingItemRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;


    // 펀딩 참여(돈 보내기)
    @Transactional
    public FundingItemResponseDto attendFunding(AttendFundingDto attendFundingDto) {
        // 사용자 정보
        Optional<Member> member = memberRepository.findById(SecurityUtil.getCurrentMemberId()); // 현재 로그인한 회원 엔티티 조회;

        // 펀딩하려는 상품 찾기
        FundingItem fundingItem = fundingItemRepository.getReferenceById(attendFundingDto.getFundingItemId());
        fundingItem.getItem();
        fundingItem.getFunding();
        // 해당 펀딩에 참여했다는 데이터 기록 (fundingItem_member 테이블)
        FundingItemMember fundingItemMember = FundingItemMember.builder().fundingItem(fundingItem).member(member.get()).fundingItemPrice(attendFundingDto.getPoint()).message(attendFundingDto.getMessage()).build();
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
        memberRepository.minusPoint(member.get().getId(), attendFundingDto.getPoint());

        return getFundingItem(attendFundingDto.getFundingItemId());

    }


    @Transactional
    public List<FundingItemResponseDto> getInvitedFundingItemList(Long fundingId) {
        List<FundingItem> invitedFundingItemList = fundingItemRepository.findByFundingId(fundingId);
        List<FundingItemResponseDto> invitedFundingItemResponseDtoList = new ArrayList<>();

        for(FundingItem fundingItem : invitedFundingItemList){
            fundingItem.getItem().getDescriptions();
            int attendMemberCount = countAttendMember(fundingItem.getId());
            List<String> arImgList = arImageRepository.findArUrlListByFundingItemId(fundingItem.getId());
            FundingItemResponseDto fundingItemResponseDto = FundingItemResponseDto.createFundingItemDto(fundingItem, attendMemberCount, arImgList, fundingItem.getFunding().getMember().getUsername());

            invitedFundingItemResponseDtoList.add(fundingItemResponseDto);
        }
        return invitedFundingItemResponseDtoList;
    }

    @Transactional
    public FundingItemResponseDto getFundingItem(Long fundingItemId) {
        FundingItem fundingItem = fundingItemRepository.getReferenceById(fundingItemId);
        String hostName = fundingItem.getFunding().getMember().getUsername();
        int attendMemberCount = countAttendMember(fundingItemId);
        List<String> arImgList = arImageRepository.findArUrlListByFundingItemId(fundingItemId);
        FundingItemResponseDto fundingItemResponseDto = FundingItemResponseDto.createFundingItemDto(fundingItem, attendMemberCount, arImgList, hostName);

        return fundingItemResponseDto;
    }

    public List<FundingItemAttendedMemberResponseDto> getAttendMember(Long fundingItemId) {
        List<FundingItemMember> fundingItemMemberList = fundingItemMemberRepository.findAllByFundingItemId(fundingItemId);

        List<FundingItemAttendedMemberResponseDto> fundingItemAttendedMemberResponseDtoList = new ArrayList<>();

        for (FundingItemMember fundingItemMember : fundingItemMemberList) {
            FundingItemAttendedMemberResponseDto fundingItemAttendedMemberResponseDto = FundingItemAttendedMemberResponseDto.builder().id(fundingItemMember.getId()).senderName(fundingItemMember.getMember().getUsername()).fundingItemPrice(fundingItemMember.getFundingItemPrice()).message(fundingItemMember.getMessage()).build();
            fundingItemAttendedMemberResponseDtoList.add(fundingItemAttendedMemberResponseDto);
        }
        return fundingItemAttendedMemberResponseDtoList;
    }

    @Transactional
    public Boolean terminateFundingItem(Long fundingItemId) {

        // 펀딩 상품 종료
        fundingItemRepository.updateFundingItemStatusByFundingItemId(fundingItemId, false);

        // 해당 펀딩에 모든 펀딩 상품이 종료될 경우 해당 펀딩 종료시킴
        Long fundingId = fundingItemRepository.findByFundingItemId(fundingItemId).getFunding().getId();

        // 모두 종료이면
        if (!fundingItemRepository.findByFundingIdAndFundingItemStatus(fundingId, true)){
            fundingRepository.updateStatus(fundingId, false);
        }


        // 확인
        // 펀딩 진행 중이면 (펀딩이 종료 안되었으면)
        if (fundingItemRepository.findByFundingItemId(fundingItemId).isFundingItemStatus()){
            return false;
        }else{
            return true;
        }

    }


    public int countAttendMember(Long fundingItemId) {
        int attendMemberCount = fundingItemMemberRepository.countAttendMember(fundingItemId);
        return attendMemberCount;
    }
}
