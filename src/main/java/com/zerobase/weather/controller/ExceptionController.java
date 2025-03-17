package com.zerobase.weather.controller;


import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.enums.CustomeException;
import com.zerobase.weather.enums.Description;
import com.zerobase.weather.exception.WeatherApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(WeatherApiException.class)
    public ResponseEntity<?> handleWeatherException(Exception ex) {
        CustomeException customeException = CustomeException.valueOf(ex.getMessage());
        ResponseDto response = ResponseDto.builder()
                .status(customeException.getStatus())
                .errorCode(customeException.getErrorCode())
                .errorMessage(customeException.getErrorMessage())
                .description(Description.FAIL)
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        log.error(ex.getMessage(), ex);
        log.error(Arrays.toString(ex.getStackTrace()));
        ResponseDto response = ResponseDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode(CustomeException.UNEXPECTED_ERROR.getErrorCode())
                .description(Description.FAIL)
                .errorMessage(CustomeException.UNEXPECTED_ERROR.getErrorMessage())
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }
}
