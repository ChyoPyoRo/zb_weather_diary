package com.zerobase.weather.controller;


import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.enums.CustomeException;
import com.zerobase.weather.enums.Description;
import com.zerobase.weather.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleWeatherException(CustomException ex) {
        return buildErrorResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(MissingServletRequestParameterException ex) {
        return buildErrorResponse(
                CustomeException.INVALID_REQUEST.getStatus(),
                CustomeException.INVALID_REQUEST.getErrorCode(),
                CustomeException.INVALID_REQUEST.getErrorMessage()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return buildErrorResponse(
                CustomeException.INVALID_REQUEST.getStatus(),
                CustomeException.INVALID_REQUEST.getErrorCode(),
                CustomeException.INVALID_REQUEST.getErrorMessage()
                );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        log.error("Unexpected Exception: ", ex);
        return buildErrorResponse(
                CustomeException.UNEXPECTED_ERROR.getStatus(),
                CustomeException.UNEXPECTED_ERROR.getErrorCode(),
                CustomeException.UNEXPECTED_ERROR.getErrorMessage()
        );
    }
    private ResponseEntity<ResponseDto> buildErrorResponse(HttpStatus status, String errorCode, String errorMessage) {
        ResponseDto response = ResponseDto.builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .description(Description.FAIL)
                .build();
        return new ResponseEntity<>(response, status);
    }
}
