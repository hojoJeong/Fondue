package com.ssafy.fundyou1.member.controller;


import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.service.MemberService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = {"프로필"})
public class ProfileRestController {

    @Autowired
    MemberService memberService;

    public ProfileRestController( MemberService memberService) {
        this.memberService = memberService;
    }

    // 내 프로필

    @GetMapping("members/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }


}