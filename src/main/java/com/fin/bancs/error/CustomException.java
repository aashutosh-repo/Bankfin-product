package com.fin.bancs.error;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private HttpStatus status;
    private int errcode;
    private String message;

    public CustomException(String message,int errcode, HttpStatus status) {
        super(message);
        this.status = status;
        this.errcode= errcode;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }


    public int getErrcode() {
		return errcode;
	}

	@Override
    public String getMessage() {
    	
        return message;
    }
}
