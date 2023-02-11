package com.ssafy.fundyou1.Scheduler;

import com.ssafy.fundyou1.firebase.FirebaseCloudMessageService;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    FundingRepository fundingRepository;
    @Autowired
    FundingItemRepository fundingItemRepository;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;


    @Transactional
    @Scheduled(cron = "* 48 13 * * *", zone = "Asia/Seoul")
    public void run() throws IOException {
        Long nowDate = System.currentTimeMillis();

        // 종료된 펀딩 false로 변경
        fundingRepository.updateAllFundingStatus(nowDate);

        // 종료된 펀딩 리스트
        List<Funding> fundingList = fundingRepository.findNeedUpdateFunding(nowDate);


        // 종료된 펀딩의 펀딩 아이템 status false로 변경
        for( Funding funding : fundingList) {
            fundingItemRepository.updateFundingItemStatusByFundingId(funding.getId(), false);

            firebaseCloudMessageService.sendMessageTo(funding.getMember().getId(), "펀딩 종료","이제 선물 받을 수 있어요!");

        }


        System.out.println("펀딩 종료!");
    }

}
