package com.kakaobank.search.repo;

/**
 * 표준 응답
 * @author JJY
 *
 */
public enum CommonResponse{
	SUCCESS(0, "정상처리 되었습니다."),
	FAIL(-1, "요청 처리를 실패하였습니다.");
	
	int code;
	String message;
	CommonResponse(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	public String getMessage() {
		return this.message;
	}
}