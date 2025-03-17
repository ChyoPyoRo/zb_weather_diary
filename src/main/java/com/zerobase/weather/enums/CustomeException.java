package com.zerobase.weather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomeException {
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 에러가 발생했습니다.", "E001001"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "E001002"),

    API_KEY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "API-KEY가 입력되지 않았거나 가져오지 못했습니다", "E002001"),
    API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출 중 오류가 발생했습니다.", "E002002"),

    DIARY_NOT_EXIST(HttpStatus.NOT_FOUND, "해당 날짜에 일기는 존재하지 않습니다", "E003001"),
    ;


    private final HttpStatus status;
    private final String errorMessage;
    private final String errorCode;
}
