package com.ssafy.fundyou1.member.service;


import com.ssafy.fundyou1.global.security.SecurityUtil;

import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;

import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;


    // 현재 SecurityContext 에 있는 유저 정보 가져오기 - 현재 접속한 회원 프로필
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public MemberResponseDto findByMemberId(Long memberId) {
        return memberRepository.findByMemberId(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보 없음"));
    }

    @Transactional
    public Integer chargePoint(Long point) {
        return memberRepository.chargePoint(point, SecurityUtil.getCurrentMemberId());
    }

    public Integer withdrawMembership() {
        return memberRepository.withdrawMembership(SecurityUtil.getCurrentMemberId(), LocalDateTime.now());
    }

    public Optional<Member> findByLoginId(String login_id) {
        return memberRepository.findByLoginId(login_id);
    }
}
