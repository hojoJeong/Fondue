package com.ssafy.fundyou1.fund.service;

import com.ssafy.fundyou1.fund.dto.FundingItemDto;
import com.ssafy.fundyou1.fund.mapper.FundingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class FundingServiceImpl implements FundingService {
//
//    @Autowired
//    private FundingMapper fundingMapper;
//
//    @Override
//    public List<FundingItemDto> getFundingItemsInfo(List<FundingItemDto> fundings){
//
//        List<FundingItemDto> result = new ArrayList<>();
//
//        for(FundingItemDto fnd : fundings) {
//
//            FundingItemDto itemsInfo = FundingMapper.getFundingItemsInfo(fnd.getId());
//
//            result.add(itemsInfo);
//        }
//
//        return result;
//
//    }
//
//}
