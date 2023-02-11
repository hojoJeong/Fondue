package com.ssafy.fundyou1.auth.service;

import com.ssafy.fundyou1.auth.domain.KakaoSocialLoginResponse;
import com.ssafy.fundyou1.auth.domain.RefreshToken;
import com.ssafy.fundyou1.auth.domain.RefreshTokenRepository;
import com.ssafy.fundyou1.auth.infrastructure.TokenProvider;
import com.ssafy.fundyou1.global.dto.TokenDto;
import com.ssafy.fundyou1.global.dto.TokenRequestDto;
import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.member.dto.request.MemberLoginRequestDto;
import com.ssafy.fundyou1.member.dto.request.MemberRequestDto;
import com.ssafy.fundyou1.member.dto.response.MemberResponseDto;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 카카오 API로 정보 가져오는 로직
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

    // 회원가입 로직

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByLoginId(memberRequestDto.getLoginId())) {
            logger.debug("이미 가입된 회원입니다.");
            return MemberResponseDto.of(memberRepository.findByLoginId(memberRequestDto.getLoginId()).get());
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    // 로그인 하는 로직 토큰발급

    @Transactional
    public TokenDto login(MemberLoginRequestDto memberLoginRequestDto) {

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    // 토큰 재발급 하는 로직

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
