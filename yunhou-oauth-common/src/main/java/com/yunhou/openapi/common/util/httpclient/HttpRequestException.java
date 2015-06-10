package com.yunhou.openapi.common.util.httpclient;

public class HttpRequestException extends Exception {
	private String entity;
	private int httpCode;
	private String errorMessage;

	public HttpRequestException(String errorMessage, int httpCode, String entity) {
		this.errorMessage = errorMessage;
		this.httpCode = httpCode;
		this.entity = entity;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
