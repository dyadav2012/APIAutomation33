package com.RestCountrier.APIAutomation.Utilities;

public class ApiResponse {
	String response;
	Integer statusCode;

	ApiResponse(String response, Integer statusCode) {
		this.response = response;
		this.statusCode = statusCode;
	}

	public String getResponse() {
		return this.response;
	}

	public Integer getstatusCode() {
		return this.statusCode;
	}

}
