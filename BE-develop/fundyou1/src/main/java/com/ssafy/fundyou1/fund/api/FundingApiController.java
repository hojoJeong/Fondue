package com.ssafy.fundyou1.fund.api;

import com.ssafy.fundyou1.fund.dto.AttendFundingDto;
import com.ssafy.fundyou1.fund.dto.FundingItemDto;
import com.ssafy.fundyou1.fund.dto.InvitedMemberDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.entity.InvitedMember;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.service.FundingItemMemberService;
import com.ssafy.fundyou1.fund.service.FundingService;
import com.ssafy.fundyou1.fund.service.InvitedMemberService;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<InvitedMember>> getInvitedFundingList() {
        return ResponseEntity.status(HttpStatus.OK).body(invitedMemberService.getInvitedFundingList());
    }


    // 참여한 펀딩 리스트 확인
    @GetMapping("/attendList")
    public ResponseEntity<List<FundingItemMember>> getAttendedFundingList() {
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemMemberService.getAttendedFundingList());
    }


}
