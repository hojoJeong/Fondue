package com.ssafy.fundyou1.fund.api;

import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.service.FundingService;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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


    // 펀딩 시작
    @PostMapping("/create")
    public List<FundingItem> createFunding() {

        // 펀딩 만들기
        List<FundingItem> createdFundingList = fundingService.createFunding();

        return createdFundingList;

    }

    // 시작한 펀딩에 장바구니 아이템 담기
//    @GetMapping("/{fundingId}")
//    public String getFunding(@PathVariable Long fundingId){
//
//        Optional<Funding> fundingFlag = fundingRepository.findById(fundingId);
//        if (fundingFlag == null){
//            return "해당 펀딩은 존재하지 않습니다.";
//        }else {
//            Funding foundFunding = fundingService.findMyFunding(fundingId);
//            // 장바구니 아이템 펀딩으로 다 넣어주기
//            List<FundingItem> nowFundingItemList = fundingService.addFundingItems(foundFunding);
//
//            return nowFundingItemList.toString();
//        }
//
//    }


}
