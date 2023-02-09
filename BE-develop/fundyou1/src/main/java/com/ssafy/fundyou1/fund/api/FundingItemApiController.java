package com.ssafy.fundyou1.fund.api;


import com.ssafy.fundyou1.fund.dto.AttendFundingDto;
import com.ssafy.fundyou1.fund.dto.FundingItemDto;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.service.FundingItemService;
import com.ssafy.fundyou1.fund.service.FundingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/funding/item")
public class FundingItemApiController {

    @Autowired
    private FundingItemService fundingItemService;


    // 펀딩 참여(돈 보내기)
    @ApiOperation(value = "펀딩 참여(돈 보내기)", notes = "참여한 펀딩 아이템 정보")
    @PostMapping("/attend")
    public ResponseEntity<FundingItemDto> attendFunding(@RequestBody AttendFundingDto attendFundingDto){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.attendFunding(attendFundingDto));
    }


    // 펀딩 아이템 한개 찾기
    @ApiOperation(value = "펀딩 아이템 상세보기", notes = "펀딩 아이템 정보")
    @PostMapping()
    public ResponseEntity<FundingItemDto> getFundingItem(@RequestBody Long fundingItemId){
        fundingItemService.getFundingItem(fundingItemId);
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.getFundingItem(fundingItemId));
    }



    // 특정 펀딩의 펀딩 아이템 리스트 (초대장으로 들어올 경우, 펀딩 통계 화면)
    @ApiOperation(value = "특정 펀딩의 아이템 리스트", notes = "펀딩 아이템 정보 리스트")
    @PostMapping("/list")
    public ResponseEntity<List<FundingItem>> getInvitedFundingItemList(@RequestBody Long fundingId){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.getInvitedFundingItemList(fundingId));
    }


    // 해당 펀딩 상품에 참여한 멤버 (선물한 친구)
    @ApiOperation(value = "펀딩 상품 한개에 참여한 멤버 리스트", notes = "참여 멤버 정보(이름, 펀딩금액, 메세지) 리스트 반환")
    @PostMapping("/memberList")
    public ResponseEntity<List<FundingItemMember>> getAttendMember(@RequestBody Long fundingItemId){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.getAttendMember(fundingItemId));
    }



}
