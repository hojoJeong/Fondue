package com.ssafy.fundyou1.member.service;


import com.ssafy.fundyou1.global.exception.BusinessException;
import com.ssafy.fundyou1.global.exception.ErrorCode;
import com.ssafy.fundyou1.member.dto.request.CheckDuplicateRequest;
import com.ssafy.fundyou1.member.dto.request.MemberSaveRequest;
import com.ssafy.fundyou1.member.dto.response.CheckDuplicateResponse;
import com.ssafy.fundyou1.member.entity.Member;
import com.ssafy.fundyou1.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_BY_LOGIN_ID));
    }



    @Transactional(readOnly = true)
    public Member findByLoginIdAndDeletedAtNull(String loginId) {
        return memberRepository.findByLoginIdAndDeletedAtNull(loginId)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_BY_LOGIN_ID));
    }

    @Transactional
    public Long saveMember(MemberSaveRequest request) {
        checkDuplicateLoginId(request.getLoginId());
        Member member = request.toMember();
        member.encodePassword(passwordEncoder);
        return memberRepository.save(member).getId();
    }






    public void checkDuplicateLoginId(String loginId) {
        if (memberRepository.existsByLoginId(loginId)) {
            throw new BusinessException(ErrorCode.MEMBER_LOGIN_ID_DUPLICATED);
        }
    }


    @Transactional(readOnly = true)
    public CheckDuplicateResponse checkExistLoginId(CheckDuplicateRequest request) {
        return CheckDuplicateResponse.from(memberRepository.existsByLoginId(request.getValue()));
    }

}
