package com.ssafy.fundyou1.member.controller;

import com.ssafy.fundyou1.member.dto.request.MemberChangeRequest;
import com.ssafy.fundyou1.member.dto.response.MemberInfoResponse;
import com.ssafy.fundyou1.member.service.ProfileService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = {"프로필"})
public class ProfileRestController {

    private final ProfileService profileService;

    public ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/auth/members")
    @ApiOperation(value = "회원 정보 조회", notes = "(로그인 필요) 회원 정보 조회")
    @ApiResponses({
        @ApiResponse(code = 404, message = "NOT FOUND\n존재하지 않는 로그인 아이디(M01)")
    })
    public ResponseEntity<MemberInfoResponse> showMemberInfo(@ApiParam(hidden = true) @AuthenticationPrincipal String username) {
        return ResponseEntity.ok().body(profileService.showMemberInfo(username));
    }


}
