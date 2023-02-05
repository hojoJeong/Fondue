package com.ssafy.fundyou1.fund.api;

import com.ssafy.fundyou1.fund.entity.FundingItem;
import com.ssafy.fundyou1.fund.service.FundingItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class FundingItemApiController {

//    // 장바구니 펀딩 아이템 펀딩 아이템으로 가져가기
//    @PostMapping("/funding/create/{cartId}")
//    public ResponseEntity<List<FundingItem>> getFundingItems(@PathVariable Long cartId){
//        List<FundingItem> dtos = FundingItemService.getFundingItems(cartId);
//
//        return ResponseEntity.status(Httpstatus.OK).body(dtos);
//
//    }

}
