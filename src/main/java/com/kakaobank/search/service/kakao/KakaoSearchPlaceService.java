package com.kakaobank.search.service.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import com.kakaobank.search.model.ApiVendor;
import com.kakaobank.search.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoSearchPlaceService {
	
	@Autowired
	KakaoSearchApi kakaoSearchApi;
	
	public ResponseEntity<String> callApi(HttpHeaders headers, String query) {
		String sToken = kakaoSearchApi.getToken();
		String sHost = kakaoSearchApi.getHost();
		String sUri = (String) kakaoSearchApi.getUri().get("keyword");
		String sAuthHeader = kakaoSearchApi.getAuthHeader() + " ";
		
		headers.add("Authorization", sAuthHeader + sToken );    
		 
		String uri = "";
			uri = sHost + sUri + "?query=" + query;
		JsonObject gson = new JsonObject();
	 
	    ResponseEntity<String> result = null;
	    result = CommonUtil.callApiGet(uri, gson, headers);
	    
	    return result;
	}
	
	/**
	 * 카카오 검색 API 환경 설정
	 * @author JJY
	 *
	 */
	@Component
	@ConfigurationProperties(prefix = "api.kakaosearch")
	public class KakaoSearchApi extends ApiVendor{
		
	}
}
;