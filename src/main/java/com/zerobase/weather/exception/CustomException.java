package com.zerobase.weather.exception;

import com.zerobase.weather.enums.CustomeException;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final CustomeException customeException;

    public CustomException(CustomeException customeException) {
        super(customeException.getErrorMessage());
        this.customeException = customeException;
    }

    public HttpStatus getStatus() {
        return customeException.getStatus();
    }

    public String getErrorCode() {
        return customeException.getErrorCode();
    }
}
