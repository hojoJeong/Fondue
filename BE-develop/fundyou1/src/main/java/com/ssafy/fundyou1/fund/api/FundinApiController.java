package com.ssafy.fundyou1.fund.api;

import com.ssafy.fundyou1.fund.dto.FundingDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.service.FundingService;
import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funding")
public class FundinApiController {
    @Autowired
    private FundingService fundingService;

//    // 펀딩 시작 (펀딩 데이터 베이스에 저장)
//    @PostMapping("/create/{userId}")
//    public ResponseEntity<List<Funding>> createFundingList(@PathVariable Long userId) {
//
//        // 펀딩 생성
//        List<FundingDto> created = fundingService.createFunding(userId);
//
//        return "/start";
//    }

    @PostMapping("/create")
    public String createFunding(Member member) {
        // 펀딩 만들기
        Long fundingId = fundingService.createFunding(member);

        // 장바구니 아이템 펀딩으로 다 넣어주기
        List<FundingItem> nowFundingItemList = fundingService.addFundingItems(member, fundingId)

    }


}
