package com.ssafy.fundyou1.member.controller;

import com.ssafy.fundyou1.member.dto.request.MemberSaveRequest;
import com.ssafy.fundyou1.member.dto.response.MemberInfoResponse;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.service.MemberService;
import com.ssafy.fundyou1.member.service.ProfileService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = {"프로필"})
public class ProfileRestController {

    private final ProfileService profileService;

    private final MemberService memberService;

    public ProfileRestController(ProfileService profileService, MemberService memberService) {
        this.profileService = profileService;
        this.memberService = memberService;
    }

    @GetMapping("/auth/members")
    @ApiOperation(value = "회원 정보 조회", notes = "(로그인 필요) 회원 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 404, message = "NOT FOUND\n존재하지 않는 로그인 아이디(M01)")
    })
    public ResponseEntity<MemberInfoResponse> showMemberInfo(@AuthenticationPrincipal String loginId) {
        return ResponseEntity.ok().body(profileService.showMemberInfo(loginId));
    }


}