package com.zerobase.weather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomeException {
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 에러가 발생했습니다.", "E001001"),


    API_KEY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "API-KEY가 입력되지 않았거나 가져오지 못했습니다", "E002001"),
    API_CALL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "API 호출 중 오류가 발생했습니다.", "E002002");


    private final HttpStatus status;
    private final String errorMessage;
    private final String errorCode;
}
