package com.ssafy.fundyou1.member.controller;

import com.ssafy.fundyou1.auth.service.AuthService;
import com.ssafy.fundyou1.cart.service.CartService;
import com.ssafy.fundyou1.member.dto.request.MemberRequestDto;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.service.MemberService;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = {"회원"})
public class MemberRestController {

    @Autowired
    AuthService authService;



    @PostMapping("/members")
    @ApiOperation(value = "회원가입", notes = "회원 가입")
    @ApiResponses({
        @ApiResponse(code = 409, message = "CONFLICT\n로그인 아이디 중복(M02)\n닉네임 중복(M03)")
    })
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

}
