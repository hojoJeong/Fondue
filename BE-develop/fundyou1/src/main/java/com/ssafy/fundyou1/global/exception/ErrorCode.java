package com.ssafy.fundyou1.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // global
    GLOBAL_ILLEGAL_ERROR(HttpStatus.BAD_REQUEST, "ILLEGAL 에러입니다.", "Z01"),
    GLOBAL_INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "RUNTIME 에러입니다.", "Z02"),

    // access token
    INVALID_EXPIRED_JWT(HttpStatus.FORBIDDEN, "만료된 토큰입니다.", "J01"),
    INVALID_MALFORMED_JWT(HttpStatus.UNAUTHORIZED, "잘못된 토큰 서명입니다.", "J02"),
    INVALID_UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원하지 않는 토큰입니다.", "J03"),
    INVALID_ILLEGAL_ARGUMENT_JWT(HttpStatus.UNAUTHORIZED, "토큰이 잘못되었습니다.", "J04"),
    INVALID_LOGOUT_USER_JWT(HttpStatus.UNAUTHORIZED, "로그아웃된 유저입니다", "J05"),
    INVALID_NOT_FOUND_AUTHORITY(HttpStatus.NOT_FOUND, "토큰에 권한값이 존재하지 않습니다.", "J06"),

    // refresh token
    INVALID_NOT_MATCH_BY_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 일치하지 않습니다", "J10"),
    INVALID_EXPIRED_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "만료된 리프레시 토큰입니다.", "J11"),
    INVALID_MALFORMED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 리프레시 토큰 서명입니다.", "J12"),
    INVALID_UNSUPPORTED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 리프레시 토큰입니다.", "J13"),
    INVALID_ILLEGAL_ARGUMENT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레쉬 토큰이 잘못되었습니다.", "J14"),

    // login
    LOGIN_GOOGLE_RESPONSE_FAIL(HttpStatus.BAD_REQUEST, "구글의 응답을 받는데 실패했습니다.", "L01"),
    LOGIN_GOOGLE_USER_INFO_RESPONSE_FAIL(HttpStatus.BAD_REQUEST, "구글 유저 정보를 얻는데 실패했습니다.", "L02"),

    // member
    MEMBER_NOT_FOUND_BY_LOGIN_ID(HttpStatus.NOT_FOUND, "존재하지 않는 로그인 아이디입니다.", "M01"),
    MEMBER_LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 로그인 아이디 입니다.", "M02"),
    MEMBER_USERNAME_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 닉네임 입니다.", "M03"),
    MEMBER_LOGIN_ERROR_BY_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다", "M04"),
    MEMBER_USERNAME_DUPLICATED_BECAUSE_OF_YOU(HttpStatus.CONFLICT, "회원님이 이미 사용 중인 닉네임입니다.", "M05"),
    MEMBER_PHONE_NUMBER_DUPLICATED(HttpStatus.CONFLICT, "이미 인증된 전화번호 입니다.", "M06"),
    MEMBER_NOT_SOCIAL_USER(HttpStatus.CONFLICT, "해당 회원은 소셜 아이디 로그인 회원이 아닙니다.", "M07"),
    MEMBER_NOT_YOUR_PHONE_NUMBER(HttpStatus.CONFLICT, "회원의 전화번호가 아닙니다.", "M08"),

    // certify
    CERTIFY_NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 아이디로 인증 요청을 한 기록이 없습니다.", "I01"),
    CERTIFY_IS_EXPIRED_CODE(HttpStatus.FORBIDDEN, "만료된 코드입니다.", "I02"),
    CERTIFY_NOT_MATCH_CODE(HttpStatus.NOT_FOUND, "일치하지 않는 인증 코드입니다.", "I03"),
    CERTIFY_NOT_MATCH_PHONE_NUMBER(HttpStatus.NOT_FOUND, "인증 요청 했던 번호와 다른 번호입니다.", "I04"),
    CERTIFY_NOT_MATCH_CHANGE_TOKEN(HttpStatus.NOT_FOUND, "비밀번호 변경 토큰을 찾을 수 없습니다.", "I05"),
    CERTIFY_IS_EXPIRED_TOKEN(HttpStatus.FORBIDDEN, "이미 사용된 토큰입니다.", "I06"),

    // authority
    AUTHORITY_NOT_FOUND_BY_AUTHORITY_CODE(HttpStatus.NOT_FOUND, "존재하지 않는 권한코드입니다.", "A01"),
    AUTHORITY_ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "접근 권한이 없는 권한코드입니다.", "A02"),
    AUTHORITY_ENTRY_POINT(HttpStatus.UNAUTHORIZED, "오류가 있는 권한코드입니다.", "A03"),

//    // SMS
//    SMS_TOO_MANY_REQUEST(HttpStatus.CONFLICT, "휴대폰 인증은 하루에 3회만 가능합니다", "P01"),
//
//    // contract
//    CONTRACT_NOT_FOUND_MEMBER_TYPE(HttpStatus.NOT_FOUND, "요청 타입이 \"seller\" 혹은 \"buyer\"가 아닙니다.", "K01"),
//    CONTRACT_NOT_SELLER(HttpStatus.CONFLICT, "현재 로그인한 회원이 Seller가 아닙니다.", "K02"),
//    CONTRACT_NOT_BUYER(HttpStatus.CONFLICT, "현재 로그인한 회원이 Buyer가 아닙니다.", "K03"),
//    CONTRACT_ALREADY_AGREE(HttpStatus.BAD_REQUEST, "이미 계약 요청을 수락한 회원입니다.", "K04"),
//    CONTRACT_NOT_FOUND_CONTRACT_AGREE(HttpStatus.NOT_FOUND, "계약 요청을 찾을 수 없습니다.", "K05"),
//    CONTRACT_NOT_YOUR_CONTRACT_AGREE(HttpStatus.BAD_REQUEST, "당신의 계약 요청이 아닙니다.", "K06"),
//    CONTRACT_AGREE_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "이미 끝난 요청입니다.", "K07"),
//    CONTRACT_NOT_FOUND_CONTRACT(HttpStatus.NOT_FOUND, "계약서를 찾을 수 없습니다.", "K08"),
//    CONTRACT_ALREADY_FINISHED(HttpStatus.BAD_REQUEST, "이미 작성이 완료된 계약서 입니다.", "K09"),
//    CONTRACT_WEIRD_LEVEL(HttpStatus.CONFLICT, "존재하지 않는 단계입니다.", "K10"),
//    CONTRACT_NULL_DATA(HttpStatus.BAD_REQUEST, "빈공간이 존재합니다.", "K11"),
//    CONTRACT_NOT_YOUR_CONTRACT(HttpStatus.BAD_REQUEST, "당신의 계약서가 아닙니다.", "K12"),

    // house
//    HOUSE_NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "존재하지 않는 매물입니다", "H01"),
//    HOUSE_NOT_MATCH_BY_LOGINID(HttpStatus.BAD_REQUEST, "작성자와 유저가 일치하지 않습니다", "H02"),
//    HOUSE_ALREADY_EXISTS_BY_LOGINID(HttpStatus.BAD_REQUEST, "이미 매물을 등록한 유저입니다", "H03"),
//
//    // house_like
//    HOUSE_LIKE_ALREADY_EXISTS_BY_LOGINID(HttpStatus.BAD_REQUEST, "이미 관심 등록한 매물입니다.", "L01"),
//    HOUSE_LIKE_NOT_FOUND_BY_LOGINID(HttpStatus.NOT_FOUND, "관심 등록하지 않은 매물입니다.", "L02"),

    // chatting

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
