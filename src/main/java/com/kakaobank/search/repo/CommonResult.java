package com.kakaobank.search.repo;

import lombok.Getter;
import lombok.Setter;

/**
 * 결과값 표준
 * @author JJY
 *
 */
@Getter
@Setter
public class CommonResult {
	private boolean success;
	private int code;
	private String message;
	private String detailErrorMessage;
	
}
