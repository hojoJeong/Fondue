package com.ssafy.fundyou1.member.service;

import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.member.dto.response.MemberInfoResponse;
import com.ssafy.fundyou1.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ProfileService {

    private final int MAX_REQUEST_COUNT = 3;
    private final int EXPIRED_TIME = 3;
    private final int RANDOM_CODE_LENGTH = 6;
    private final int CHANGE_TOKEN_LENGTH = 12;

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse showMemberInfo(String loginId) {
        Member member = memberService.findByLoginIdAndDeletedAtNull(loginId);
        return MemberInfoResponse.from(member);
    }


    public void checkExpiredCode(LocalDateTime expireTime) {
        if (LocalDateTime.now().isAfter(expireTime)) {
            throw new BusinessException(ErrorCode.CERTIFY_IS_EXPIRED_CODE);
        }
    }

    public void checkCertificationCode(String certificationCode, String requestCode) {
        if (!certificationCode.equals(requestCode)) {
            throw new BusinessException(ErrorCode.CERTIFY_NOT_MATCH_CODE);
        }
    }


    public void checkTokenExpired(Boolean expired) {
        if (expired) {
            throw new BusinessException(ErrorCode.CERTIFY_IS_EXPIRED_TOKEN);
        }
    }
}
