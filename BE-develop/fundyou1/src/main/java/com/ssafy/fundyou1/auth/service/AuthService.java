package com.ssafy.fundyou1.auth.service;

import com.ssafy.fundyou1.auth.controller.dto.request.LoginRequest;
import com.ssafy.fundyou1.auth.controller.dto.request.ReissueRequest;
import com.ssafy.fundyou1.auth.controller.dto.response.TokenResponse;
import com.ssafy.fundyou1.auth.domain.KakaoSocialLoginResponse;
import com.ssafy.fundyou1.auth.domain.RefreshToken;
import com.ssafy.fundyou1.auth.domain.RefreshTokenRepository;
import com.ssafy.fundyou1.auth.infrastructure.JwtTokenProvider;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import com.ssafy.fundyou1.member.service.MemberService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;


    public AuthService(MemberService memberService, PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider, RefreshTokenRepository refreshTokenRepository,
                       MemberRepository memberRepository) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Map<String, Object> login(LoginRequest request, HttpServletResponse response) {
        Member member = memberService.findByLoginIdAndDeletedAtNull(request.getLoginId());
        member.checkPassword(passwordEncoder, request.getPassword());

        TokenResponse tokenResponse = jwtTokenProvider.createToken(member.getLoginId(),member.getAuthority());
        String refreshToken = saveRefreshToken(member, tokenResponse);
        setTokenToCookie(tokenResponse.getAccessToken(), refreshToken, response);

        Map<String,Object> result = new HashMap<>();
        result.put("accessToken", tokenResponse.getAccessToken());
        result.put("refreshToken", refreshToken);

        return result;
    }
    public KakaoSocialLoginResponse kakaoLoginService(String accessToken) {

        // body에 들어갈 parameter 생성. 하지만 넣을 값은 없다.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        // header에 들어갈 키, 값 생성
        HttpHeaders headers = new HttpHeaders();

        // 키: Authorization. 값: Bearer {accessToken}
        headers.add("Authorization", "Bearer " + accessToken);

        // spring에서 rest통신을 지원하는 RestTemplate
        RestTemplate rt = new RestTemplate();

        // setRequestFactory가 없으면 body에 아무런 값이 들어있지 않을때 500에러 발생. non-body err
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        // header + body를 하나로 묶어 entity 생성
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        // rest 통신 시작
        ResponseEntity<KakaoSocialLoginResponse> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me", // 요청할 서버 주소
                HttpMethod.POST, // 요청할 방식
                entity, // 요청할 때 보낼 데이터
                KakaoSocialLoginResponse.class // 반환타입
        );
        return response.getBody();
    }

    @Transactional
    public  Map<String,Object> saveKaKaoUser(KakaoSocialLoginResponse rEntity,HttpServletResponse response) {
        Member member = memberRepository.findByLoginId("k_" + rEntity.getId())
                .orElse(rEntity.toEntity());
        memberRepository.save(member);

        TokenResponse tokenResponse = jwtTokenProvider.createToken(member.getLoginId(),member.getAuthority());
        String refreshToken = saveRefreshToken(member, tokenResponse);
        setTokenToCookie(tokenResponse.getAccessToken(), refreshToken, response);

        Map<String,Object> result = new HashMap<>();
        result.put("accessToken", tokenResponse.getAccessToken());
        result.put("refreshToken", refreshToken);

        return result;
    }


//    @Transactional
//    public SocialMember saveSocial(KakaoSocialLoginResponse response) {
//        SocialMember social = socialRepository.findByLoginId("k_" + response.getId())
//                .orElse(response.toEntity());
//        response.toEntity();
//        // 저장
//        socialRepository.save(social);
//        return social;
//    }
//
//    @Transactional
//    public Map<String, Object> kakaoJwt(KakaoSocialLoginResponse rEntity) {
//        SocialMember social = memberService.findByLoginIdAndDeletedAtNull("k_"+rEntity.getId());
//        social.checkPassword(passwordEncoder,"fundyou"+ rEntity.getId() );
//
//        TokenResponse tokenResponse = jwtTokenProvider.createToken(social.getLoginId(),social.getAuthority());
//        String refreshToken = saveRefreshToken(social, tokenResponse);
//        setTokenToCookie(tokenResponse.getAccessToken(), refreshToken, response);
//
//        Map<String,Object> result = new HashMap<>();
//        result.put("accessToken", tokenResponse.getAccessToken());
//        result.put("refreshToken", refreshToken);
//
//        return result;
//    }





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
