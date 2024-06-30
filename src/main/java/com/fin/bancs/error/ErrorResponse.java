package com.fin.bancs.error;

public class ErrorResponse {
    private String message;
    private int errcode;
    private int status;

    public ErrorResponse(String message, int errcode, int status) {
    	if(errcode>0 ) {
    		this.message =errcode +" : "+ message;
    	}else {
    		this.message ="NOCODE" +" : "+ message;
    	}
    	this.errcode=errcode;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    

    public int getErrcode() {
		return errcode;
	}

	public int getStatus() {
        return status;
    }
}