package com.ssafy.fundyou1.auth.controller;

import com.ssafy.fundyou1.auth.controller.dto.request.LoginRequest;

import com.ssafy.fundyou1.auth.controller.dto.request.ReissueRequest;
import com.ssafy.fundyou1.auth.service.AuthService;
import com.sun.net.httpserver.HttpsServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = {"로그인"})
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/members/login")
    @ApiOperation(value = "로그인", notes = "로그인 API")
    @ApiResponses({
        @ApiResponse(code = 401, message = "UNAUTHORIZED\n일치하지 않는 비밀번호(M04)"),
        @ApiResponse(code = 404, message = "NOT FOUND\n존재하지 않는 로그인 아이디(M01)")
    })
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Map token = authService.login(request, response);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/members/reissue")
    @ApiOperation(value = "토큰 재발급", notes = "토큰을 재발급하는 API")
    @ApiResponses({
        @ApiResponse(code = 400, message = "BAD REQUEST\n일치하지 않는 리프레시 토큰(J10)"),
        @ApiResponse(code = 401, message = "UNAUTHORIZED\n잘못된 리프레시 토큰 서명(J12)\n지원하지 않는 리프레시 토큰(J13)\n"
            + "잘못된 리프레시 토큰(J14)\n로그아웃된 유저(J05)"),
        @ApiResponse(code = 403, message = "FORBIDDEN\n만료된 리프레시 토큰(J11)"),
        @ApiResponse(code = 404, message = "NOT FOUND\n권한값이 없는 엑세스 토큰(J06)")
    })
    public ResponseEntity<Map<String,Object>> reissue(@RequestBody ReissueRequest tokenRequest, HttpServletResponse response) {
        String reissueAccessToken = authService.reissue(tokenRequest, response);
        Map<String,Object> result = new HashMap<>();
        result.put("accessToken", reissueAccessToken);
        return ResponseEntity.ok().body(result);
    }

}
