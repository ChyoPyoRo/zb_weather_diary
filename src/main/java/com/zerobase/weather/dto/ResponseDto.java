package com.zerobase.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zerobase.weather.enums.Description;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto<T> {
    private HttpStatus status;
    private Description description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;

    //success
    public ResponseDto(HttpStatus status, Description description){
        this.status = status;
        this.description = description;
    }
    //fail
    @Builder
    public ResponseDto(HttpStatus status, Description description, String errorCode, String errorMessage){
        this.status = status;
        this.description = description;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    //success + data
    public ResponseDto(HttpStatus status, Description description, T data){
        this.status = status;
        this.description = description;
        this.data = data;
    }
}
