package com.ssafy.fundyou1.member.service;


import com.ssafy.fundyou1.global.security.SecurityUtil;

import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;

import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    // 탈퇴할때 필요!
    @Transactional(readOnly = true)
    public Member findByLoginIdAndDeletedAtNull(String id) {
        return memberRepository.findByLoginIdAndDeletedAtNull(id)
                .orElseThrow(() -> new RuntimeException("탈퇴할 유저 정보를 찾을 수 없습니다"));
    }


}
