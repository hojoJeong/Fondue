package com.ssafy.fundyou1.member.controller;

import com.ssafy.fundyou1.auth.service.AuthService;
import com.ssafy.fundyou1.cart.service.CartService;
import com.ssafy.fundyou1.member.dto.request.CheckDuplicateRequest;
import com.ssafy.fundyou1.member.dto.request.MemberDeleteRequest;
import com.ssafy.fundyou1.member.dto.request.MemberRequestDto;
import com.ssafy.fundyou1.member.dto.response.CheckDuplicateResponse;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.service.MemberDeleteService;
import com.ssafy.fundyou1.member.service.MemberService;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = {"회원"})
public class MemberRestController {

    private final MemberService memberService;
    private final MemberDeleteService memberDeleteService;

    private final CartService cartService;

    private final AuthService authService;



    @PostMapping("/members")
    @ApiOperation(value = "회원가입", notes = "회원 가입")
    @ApiResponses({
        @ApiResponse(code = 409, message = "CONFLICT\n로그인 아이디 중복(M02)\n닉네임 중복(M03)")
    })
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    // 회원 탈퇴 수정해야함..

//    @DeleteMapping("/member")
//    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
//    public ResponseEntity<Map<String,Object>> resignMember(@RequestBody MemberDeleteRequest request){
//        Long id = memberDeleteService.deleteMember(request);
//        Map<String,Object> result = new HashMap<>();
//        result.put("deleteID",id );
//        return ResponseEntity.ok().body(result);
//    }
}
