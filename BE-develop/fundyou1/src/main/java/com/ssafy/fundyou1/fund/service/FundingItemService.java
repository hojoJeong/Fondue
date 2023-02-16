package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.ar.repository.ArImageRepository;
import com.ssafy.fundyou1.firebase.FirebaseCloudMessageService;
import com.ssafy.fundyou1.fund.dto.AttendFundingDto;
import com.ssafy.fundyou1.fund.dto.FundingItemAttendedMemberResponseDto;
import com.ssafy.fundyou1.fund.dto.FundingItemResponseDto;
import com.ssafy.fundyou1.fund.dto.FundingResultMemberDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    MemberRepository memberRepository;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    @Lazy
    FundingService fundingService;

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
        FundingItem fundingItem = fundingItemRepository.findByFundingItemId(fundingItemId);
        Long fundingId = fundingItem.getFunding().getId();

        // 모두 종료이면
        if (!fundingItemRepository.findByFundingIdAndFundingItemStatus(fundingId, true)){
            fundingRepository.updateStatus(fundingId, false);
        }

        // 확인
        // 펀딩 진행 중이면 (펀딩이 종료 안되었으면)
        if (fundingItemRepository.findByFundingItemId(fundingItemId).isFundingItemStatus()){
            return false;
        }else{
            try {
                // 펀딩 완료 푸시 알림 : 주최자
                firebaseCloudMessageService.sendMessageTo(fundingItem.getFunding().getMember().getId(), "펀딩 종료","이제 선물 받을 수 있어요!", true);

                // 펀딩 참여한 사람 리스트

                List<InvitedMember> invitedFundingMemberList = invitedMemberRepository.findAllByFundingId(fundingId);
                for (InvitedMember invitedMember:invitedFundingMemberList) {
                    Long memberId = invitedMember.getMember().getId();
                    firebaseCloudMessageService.sendMessageTo(memberId, "펀딩 종료",fundingItem.getFunding().getMember().getUsername() + "님의 펀딩이 종료되었습니다.\n 참여해주셔서 감사합니다😊", false);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        }

    }


    public int countAttendMember(Long fundingItemId) {
        int attendMemberCount = fundingItemMemberRepository.countAttendMember(fundingItemId);
        return attendMemberCount;
    }
}
