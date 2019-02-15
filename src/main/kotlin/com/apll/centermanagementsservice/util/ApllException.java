package com.apll.centermanagementsservice.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApllException extends RuntimeException{
    private String errorMessage;
    private HttpStatus errorCode;

    public ApllException() {
    }

    public ApllException(String errorMessage,HttpStatus errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
