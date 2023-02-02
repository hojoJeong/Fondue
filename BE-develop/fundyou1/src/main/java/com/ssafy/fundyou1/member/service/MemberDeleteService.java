package com.ssafy.fundyou1.member.service;

import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberDeleteService {

    private final MemberService memberService;

    public MemberDeleteService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Transactional
    public void resignMember(String loginId){
        Member member = memberService.findByLoginIdAndDeletedAtNull(loginId);

        member.changeStatus(MemberStatus.FALSE);

        member.saveDeletedTime();
    }
}
