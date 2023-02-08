package com.ssafy.fundyou1.member.controller;


import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.service.MemberService;
import com.ssafy.fundyou1.member.service.ProfileService;
import io.swagger.annotations.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import com.ssafy.fundyou1.global.security.SecurityUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = {"프로필"})
public class ProfileRestController {

    private final ProfileService profileService;

    private final MemberService memberService;

    public ProfileRestController(ProfileService profileService, MemberService memberService) {
        this.profileService = profileService;
        this.memberService = memberService;
    }

    // 내 프로필

    @GetMapping("members/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    // 로그인 아이디로 찾기
    @GetMapping("members/{loginId}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String loginId) {
        return ResponseEntity.ok(memberService.getMemberInfo(loginId));
    }


    private String getUsername(Authentication authentication) {
        String name = authentication.getName();
        return name;
    }


}