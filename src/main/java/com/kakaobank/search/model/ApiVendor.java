package com.kakaobank.search.model;

import java.util.HashMap;

import lombok.Data;

@Data
public class ApiVendor {
	private String host;
	private String authHeader;
	private String token;
	private String clientid;
	private String clientsecret;
	private HashMap<String,Object> uri;
	
}
