package com.ssafy.fundyou1.global.exception;

public class BusinessException extends CustomException {

    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
