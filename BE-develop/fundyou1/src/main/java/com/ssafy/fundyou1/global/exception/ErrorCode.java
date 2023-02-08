package com.ssafy.fundyou1.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {


    // category
    CATEGORY_NOT_FOUND_BY_ID(HttpStatus.CONFLICT, "카테고리가 없습니다.", "C01"),
    CATEGORY_CATEGORY_NAME_DUPLICATED(HttpStatus.CONFLICT, "카테고리 아이디가 중복 이에요", "C02"),

    //item
    ITEM_TITLE_BRAND_DUPLICATED(HttpStatus.CONFLICT, "아이템 제목이랑 브랜드가 겹쳐요", "I01"),


    ;
    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    ErrorCode(HttpStatus httpStatus, String message, String code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
