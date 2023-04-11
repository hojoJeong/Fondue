package com.ssafy.fundyou1.global.exception;

// 토큰 예외 처리
public class JwtException extends CustomException {

    public JwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
