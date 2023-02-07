package com.ssafy.fundyou1.fund.api;


import com.ssafy.fundyou1.fund.dto.AttendFundingDto;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.entity.FundingItemMember;
import com.ssafy.fundyou1.fund.service.FundingItemService;
import com.ssafy.fundyou1.fund.service.FundingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/funding/item")
public class FundingItemApiController {

    @Autowired
    private FundingItemService fundingItemService;


    // 펀딩 참여(돈 보내기)
    // point 부분 수정 해야함
    @PostMapping("/attend")
    @ResponseBody
    public ResponseEntity<FundingItemMember> attendFunding(@RequestBody AttendFundingDto attendFundingDto){
        return ResponseEntity.status(HttpStatus.OK).body(fundingItemService.attendFunding(attendFundingDto));
    }



}
