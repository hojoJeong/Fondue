package com.ssafy.fundyou1.fund.api;

import com.ssafy.fundyou1.fund.dto.*;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.service.FundingItemMemberService;
import com.ssafy.fundyou1.fund.service.FundingService;
import com.ssafy.fundyou1.fund.service.InvitedMemberService;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/funding")
public class FundingApiController {
    @Autowired
    private FundingRepository fundingRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FundingService fundingService;
    @Autowired
    private InvitedMemberService invitedMemberService;
    @Autowired
    private FundingItemMemberService fundingItemMemberService;


    // 펀딩 개설
    @PostMapping("/create")
    @ResponseBody
    public Long createFunding(@RequestBody Long endDate) {

        // 펀딩 만들기
        Long createdFundingId = fundingService.createFunding(endDate);

        // 새 펀딩 아이디 리턴
        return createdFundingId;

    }


    // 초대 받은 펀딩 데이터 베이스에 저장하기
    @PostMapping("/getInvited")
    public ResponseEntity<InvitedMember> storeInvitedFunding(@RequestBody InvitedMemberDto invitedMemberDto){
        return ResponseEntity.status(HttpStatus.OK).body(invitedMemberService.storeInvitedFunding(invitedMemberDto));
    }


    // 초대 받은 펀딩 리스트 확인
    @GetMapping("/invitedList")
    public ResponseEntity<List<InvitedFundingDto>> getInvitedFundingDtoList() {
        return ResponseEntity.status(HttpStatus.OK).body(invitedMemberService.getInvitedFundingDtoList());
    }


    // 참여한 펀딩 리스트 확인 => 없어도 되는것 같음
    @GetMapping("/attendList")
    public ResponseEntity<List<FundingItemMember>> getAttendedFundingList() {
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemMemberService.getAttendedFundingList());
    }


    // 펀딩 통계 (참여 멤버)
    @PostMapping("/statistics")
    public ResponseEntity<List<FundingResultMemberDto>> fundingResultMemberDtoList(@RequestBody Long fundingId){
        return ResponseEntity.status(HttpStatus.OK).body(fundingService.fundingResultMemberDtoList(fundingId));
    }


    // 내 펀딩 리스트



}
