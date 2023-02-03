package com.ssafy.fundyou1.member.controller;

import com.ssafy.fundyou1.member.dto.request.CheckDuplicateRequest;
import com.ssafy.fundyou1.member.dto.request.MemberDeleteRequest;
import com.ssafy.fundyou1.member.dto.request.MemberSaveRequest;
import com.ssafy.fundyou1.member.dto.response.CheckDuplicateResponse;
import com.ssafy.fundyou1.member.service.MemberDeleteService;
import com.ssafy.fundyou1.member.service.MemberService;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = {"회원"})
public class MemberRestController {

    private final MemberService memberService;
    private final MemberDeleteService memberDeleteService;

    public MemberRestController(MemberService memberService, MemberDeleteService memberDeleteService) {
        this.memberService = memberService;
        this.memberDeleteService = memberDeleteService;
    }

    @PostMapping("/members")
    @ApiOperation(value = "회원가입", notes = "회원 가입")
    @ApiResponses({
        @ApiResponse(code = 409, message = "CONFLICT\n로그인 아이디 중복(M02)\n닉네임 중복(M03)")
    })
    public ResponseEntity<Map<String,Object>> join(@RequestBody MemberSaveRequest request) {
        Long joinMemberId = memberService.saveMember(request);
        URI uri = URI.create("/api/members/" + joinMemberId);
        ResponseEntity.created(uri);
        Map<String,Object> result = new HashMap<>();
        result.put("joinMemberId",joinMemberId );
        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/members/exist-login-id")
    @ApiOperation(value = "아이디 중복검사", notes = "아이디 중복 검사")
    public ResponseEntity<CheckDuplicateResponse> checkExistLoginId(@RequestBody CheckDuplicateRequest request) {
        return ResponseEntity.ok().body(memberService.checkExistLoginId(request));
    }

    @DeleteMapping("/members")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    public ResponseEntity<Map<String,Object>> resignMember(@RequestBody MemberDeleteRequest request){
        Long id = memberDeleteService.deleteMember(request);
        Map<String,Object> result = new HashMap<>();
        result.put("deleteID",id );
        return ResponseEntity.ok().body(result);
    }
}