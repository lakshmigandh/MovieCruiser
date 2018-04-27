package com.cts.sr.moviecruiser.user.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum ErrorCodes implements Serializable{
	
	USER_ID_PASSWORD_EMPTY(1000,"User Id/Password cannot be empty"),
	PASSWORD_INCORRECT(1001,"Password is incorrect"),
	USER_DOES_NOT_EXIST(1002,"User doesn't exists"),
	UNEXPECTED_ERROR(1003,"Unexpected error occurred");

	@JsonProperty("errorCode")
	private int errorCode;
	
	@JsonProperty("errorMsg")
	private String errorMsg;
	
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	private ErrorCodes(int errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}
