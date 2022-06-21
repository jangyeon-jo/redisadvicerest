package com.kakaobank.search.service.naver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import com.kakaobank.search.model.ApiVendor;
import com.kakaobank.search.util.CommonUtil;

@Service
public class NaverSearchPlaceService {
	
	@Autowired
	NaverSearchApi naverSearchApi;
	
	public ResponseEntity<String> callApi(HttpHeaders headers ,String query) {
		String sHost = naverSearchApi.getHost();
		String sUri = (String) naverSearchApi.getUri().get("keyword");
		String sClientId = naverSearchApi.getClientid();
		String sClientSecret = naverSearchApi.getClientsecret();
		String sCnt = "10";
		
		//&display=10&start=1&sort=random
		String uri = sHost + sUri + "?query="+ query + "&display=10&start=1&sort=random";
		
	    headers.add("X-Naver-Client-Id", sClientId);     
	    headers.add("X-Naver-Client-Secret", sClientSecret);     
	    
	    ResponseEntity<String> result = null;
	    JsonObject gson = new JsonObject();
	    result = CommonUtil.callApiGet(uri, gson, headers);
	    
	    return result;
	}
	
	/**
	 * 네이버 검색 API 환경 설정
	 * @author JoJang
	 *
	 */
	@Component
	@ConfigurationProperties(prefix = "api.naversearch")
	public class NaverSearchApi extends ApiVendor{
	}
}
