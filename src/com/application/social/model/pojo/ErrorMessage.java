package com.application.social.model.pojo;

public class ErrorMessage {

	private String response;
	private String status;
	private int errorCode;
	private String errorMessage;

	public ErrorMessage() {
	}

	public ErrorMessage(String response, String status, int errorCode, String errorMessage) {
		this.response = response;
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
