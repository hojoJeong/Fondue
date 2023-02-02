package com.ssafy.fundyou1.global.exception;

public class JwtException extends CustomException {

    public JwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
