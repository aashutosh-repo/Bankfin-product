package com.test.bank.error;

public class ErrorResponse {
	private final int errorCode;
	private final String errorMessage;

	public ErrorResponse(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}