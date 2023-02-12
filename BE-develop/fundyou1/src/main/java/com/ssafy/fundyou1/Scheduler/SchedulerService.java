package com.ssafy.fundyou1.Scheduler;

import com.ssafy.fundyou1.firebase.FirebaseCloudMessageService;
import com.ssafy.fundyou1.fund.dto.FundingResultMemberDto;
import com.ssafy.fundyou1.fund.entity.Funding;
import com.ssafy.fundyou1.fund.repository.FundingItemMemberRepository;
import com.ssafy.fundyou1.fund.repository.FundingItemRepository;
import com.ssafy.fundyou1.fund.repository.FundingRepository;
import com.ssafy.fundyou1.fund.service.FundingService;
import com.ssafy.fundyou1.member.repository.MemberRepository;
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
    @Autowired
    private FundingItemMemberRepository fundingItemMemberRepository;

    @Autowired
    FundingService fundingService;

    @Autowired
    MemberRepository memberRepository;


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

            // 펀딩 완료 푸시 알림 : 주최자
            firebaseCloudMessageService.sendMessageTo(funding.getMember().getId(), "펀딩 종료","이제 선물 받을 수 있어요!");

            // 펀딩 참여한 사람 리스트
            List<FundingResultMemberDto> inviteFundingMemberList = fundingService.fundingResultMemberDtoList(funding.getId());

            // 참여자가 3명이상일때 미만일때 경우의 수
            if(inviteFundingMemberList.size() >= 3) {
                //상위 3명과 나머지 참여자 경우의 수
                for (int i = 0; i < inviteFundingMemberList.size(); i++) {
                    if(i == 0) {
                        inviteFundingMemberList.get(i);
                        firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(i).getMemberId(), "참여한 펀딩 종료","1등 이에요! 감사합니다.");
                    } else if (i == 1) {
                        inviteFundingMemberList.get(i);
                        firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(i).getMemberId(), "참여한 펀딩 종료","2등 이에요! 감사합니다.");
                    } else if (i == 2) {
                        inviteFundingMemberList.get(i);
                        firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(i).getMemberId(), "참여한 펀딩 종료","3등 이에요! 감사합니다.");
                    } else {
                        inviteFundingMemberList.get(i);
                        firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(i).getMemberId(), "참여한 펀딩 종료","선물 해주셔서 감사합니다.");
                    }
                }
            }
            // 2명 참여 했을때 , 1, 2등한테만 알림 보내주기
            else if (inviteFundingMemberList.size() == 2) {
                for (int i = 0; i < inviteFundingMemberList.size(); i++) {
                    if(i == 0) {
                        inviteFundingMemberList.get(i);
                        firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(i).getMemberId(), "참여한 펀딩 종료","1등 이에요! 감사합니다.");
                    } else {
                        inviteFundingMemberList.get(i);
                        firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(i).getMemberId(), "참여한 펀딩 종료","2등 이에요! 감사합니다.");
                    }
                }
            }
            // 1명 참여한 경우
            else if (inviteFundingMemberList.size() == 1) {
                firebaseCloudMessageService.sendMessageTo(inviteFundingMemberList.get(0).getMemberId(), "참여한 펀딩 종료","1등 이에요! 감사합니다.");
            }
            // 아무 참여자 없이 강제 종료
            else {
                System.out.println("참여자가 없습니다");
            }
        }
        System.out.println("펀딩 종료!");
    }

}
