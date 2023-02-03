package com.ssafy.fundyou1.member.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ssafy.fundyou1.member.dto.request.MemberDeleteRequest;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberDeleteService {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    public MemberDeleteService(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }


    @Transactional
    public Long deleteMember(MemberDeleteRequest request) {
        Member member = memberService.findByLoginIdAndDeletedAtNull(request.getLoginId());
        member.deleteMember(member.getLoginId());
        member.changeStatus(member.getLoginId());
        member.saveDeletedTime();
        return member.getId();
    }

}
