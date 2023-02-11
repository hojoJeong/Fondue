package com.ssafy.fundyou1.member.controller;

import com.ssafy.fundyou1.auth.service.AuthService;
import com.ssafy.fundyou1.cart.service.CartService;
import com.ssafy.fundyou1.global.dto.BaseResponseBody;
import com.ssafy.fundyou1.member.dto.request.MemberRequestDto;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.service.MemberService;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@Api(tags = {"회원"})
public class MemberRestController {

    @Autowired
    AuthService authService;

    @Autowired
    MemberService memberService;

    @PostMapping()
    @ApiOperation(value = "회원가입", notes = "회원 가입")
    @ApiResponses({
        @ApiResponse(code = 409, message = "CONFLICT\n로그인 아이디 중복(M02)\n닉네임 중복(M03)")
    })
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @GetMapping("/{point}")
    @ApiOperation(value = "포인트 충전", notes = "로그인된 회원의 계정에 포인트 충전")
    public ResponseEntity<Integer> chargePoint(@PathVariable Long point){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.chargePoint(point));
    }

    @GetMapping("/withdrawal")
    @ApiOperation(value = "회원 탈퇴", notes = "로그인된 회원 계정 탈퇴")
    public ResponseEntity<BaseResponseBody> withdrawMembership(){
        if(memberService.withdrawMembership() == 1){
            return ResponseEntity.ok().body(BaseResponseBody.of(200, "success", 1));
        }else {
            return ResponseEntity.ok().body(BaseResponseBody.of(404, "User not found", 0));
        }
    }

}
