package com.ssafy.fundyou1.auth.service;

import com.ssafy.fundyou1.auth.controller.dto.request.LoginRequest;
import com.ssafy.fundyou1.auth.controller.dto.request.ReissueRequest;
import com.ssafy.fundyou1.auth.controller.dto.response.TokenResponse;
import com.ssafy.fundyou1.auth.domain.RefreshToken;
import com.ssafy.fundyou1.auth.domain.RefreshTokenRepository;
import com.ssafy.fundyou1.auth.infrastructure.JwtTokenProvider;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    public AuthService(MemberService memberService, PasswordEncoder passwordEncoder,
        JwtTokenProvider jwtTokenProvider, RefreshTokenRepository refreshTokenRepository) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public String login(LoginRequest request, HttpServletResponse response) {
        Member member = memberService.findByLoginIdAndDeletedAtNull(request.getLoginId());
        member.checkPassword(passwordEncoder, request.getPassword());

        TokenResponse tokenResponse = jwtTokenProvider.createToken(member.getLoginId(),member.getAuthority());
        String refreshToken = saveRefreshToken(member, tokenResponse);
        setTokenToCookie(tokenResponse.getAccessToken(), refreshToken, response);
        return tokenResponse.getAccessToken();
    }

    @Transactional
    public String saveRefreshToken(Member member, TokenResponse tokenResponse) {
        RefreshToken refreshToken = refreshTokenRepository.findBySubject(member.getLoginId())
            .orElse(RefreshToken.builder()
                .subject(member.getLoginId())
                .expiredAt(LocalDateTime.now().plusDays(7))
                .build());

        refreshToken.updateRefreshToken(tokenResponse.getRefreshToken());
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }

    public void setTokenToCookie(String accessToken, String refreshToken, HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7days, 기간 지난 access token 도 필요하다고 함
        accessTokenCookie.setSecure(true);
//        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        refreshTokenCookie.setSecure(true);
//        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);
    }

    @Transactional
    public String reissue(ReissueRequest request, HttpServletResponse response) {
        jwtTokenProvider.validateRefreshToken(request.getRefreshToken());
        Authentication authentication = jwtTokenProvider.getAuthentication(request.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findBySubject(authentication.getName())
            .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_LOGOUT_USER_JWT));

        refreshToken.validateValue(request.getRefreshToken());

        TokenResponse tokenResponse = jwtTokenProvider.createToken(authentication.getName(),
            jwtTokenProvider.getAuthority(authentication));

        refreshToken.updateRefreshToken(tokenResponse.getRefreshToken());
        refreshTokenRepository.save(refreshToken);
        setTokenToCookie(tokenResponse.getAccessToken(), refreshToken.getRefreshToken(), response);
        return tokenResponse.getAccessToken();
    }

    @Transactional
    public void deleteRefreshTokenTable() {
        refreshTokenRepository.deleteByExpiredAtBefore(LocalDateTime.now());
    }
}
