package com.ssafy.fundyou1.fund.api;


import com.ssafy.fundyou1.fund.dto.*;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.service.FundingItemService;
import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/fundingItem")
@Api(tags = {"펀딩 아이템"})
public class FundingItemApiController {
    @Autowired
    private FundingItemRepository fundingItemRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FundingItemService fundingItemService;

    @Autowired
    private MemberService memberService;


    // 펀딩 참여(돈 보내기)
    @ApiOperation(value = "펀딩 참여(돈 보내기)", notes = "참여한 펀딩 아이템 정보")
    @PostMapping("/attend")
    public ResponseEntity attendFunding(@RequestBody AttendFundingDto attendFundingDto) {

        FundingItem fundingItem = fundingItemRepository.findByFundingItemId(attendFundingDto.getFundingItemId());
        int restFundingPrice = fundingItem.getItemTotalPrice() - fundingItem.getCurrentFundingPrice();

        int holdMoney = memberService.getMyInfo().getPoint();


        if (holdMoney < attendFundingDto.getPoint()) {
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잔액이 부족합니다.", null));
        } else if (restFundingPrice < attendFundingDto.getPoint()) {
            // 남은 펀딩 금액 보다 큰 금액을 펀딩하려는 경우
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "가능한 펀딩 금액을 초과했습니다.", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "펀딩 성공", fundingItemService.attendFunding(attendFundingDto)));
        }

    }


    // 펀딩 아이템 한개 찾기
    @ApiOperation(value = "펀딩 아이템 상세보기", notes = "펀딩 아이템 정보")
    @PostMapping()
    public ResponseEntity<FundingItemResponseDto> getFundingItem(@RequestBody FundingItemIdDto fundingItemIdDto){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.getFundingItem(fundingItemIdDto.getFunding_item_id()));
    }



    // 특정 펀딩의 펀딩 아이템 리스트 (초대장으로 들어올 경우, 펀딩 통계 화면)
    @ApiOperation(value = "특정 펀딩의 아이템 리스트", notes = "펀딩 아이템 정보 리스트")
    @PostMapping("/list")
    public ResponseEntity<List<FundingItemResponseDto>> getInvitedFundingItemList(@RequestBody FundingIdDto fundingIdDto){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.getInvitedFundingItemList(fundingIdDto.getFundingId()));
    }


    // 해당 펀딩 상품에 참여한 멤버 (선물한 친구)
    @ApiOperation(value = "펀딩 상품 한개에 참여한 멤버 리스트", notes = "참여 멤버 정보(이름, 펀딩금액, 메세지) 리스트 반환")
    @PostMapping("/memberList")
    public ResponseEntity<List<FundingItemAttendedMemberResponseDto>> getAttendMember(@RequestBody FundingItemIdDto fundingItemIdDto){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.getAttendMember(fundingItemIdDto.getFunding_item_id()));
    }


    // 펀딩 아이템 종료
    @PostMapping("/terminate")
    @ApiOperation(value = "펀딩 아이템 펀딩 종료", notes = "반환값 - true ")
    public ResponseEntity<Boolean> terminateFundingItem(@RequestBody FundingItemIdDto fundingItemIdDto) {
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.terminateFundingItem(fundingItemIdDto.getFunding_item_id()));
    }



}
