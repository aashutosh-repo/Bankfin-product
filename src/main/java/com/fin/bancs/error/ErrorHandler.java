package com.fin.bancs.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorHandler extends RuntimeException {
    public ErrorHandler(String message) {
        super(message);
    }
}