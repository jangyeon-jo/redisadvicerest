package com.kakaobank.search.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;


import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import lombok.extern.slf4j.Slf4j;

public class CommonUtil {

	@Autowired
	static RestTemplate restTemplate;

	public static String getHashKey(String jsonStr, String key, String charset) {
		String hash = "";
		try {
			Mac sha256HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256HMAC.init(secretKey);

			hash = Base64.encodeBase64String(sha256HMAC.doFinal(jsonStr.getBytes(charset)));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return hash;
	}
	public static ResponseEntity<String> callApi(String uri, JsonObject body, HttpHeaders httpHeaders){
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}
 
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
 
        CloseableHttpClient httpClient = HttpClients.custom()
        		.setMaxConnTotal(50)
                .build();
 
        requestFactory.setHttpClient(httpClient);

		HttpEntity<String> requestEntity = new HttpEntity<String>(body.toString(), httpHeaders);																																																																																																																																																															
		
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

	ResponseEntity<String> result = null;
	try {
		//RequestHeader 직접 설정할 때 사용.
		result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);


	} catch (RestClientException e) {
		//log.debug("RestClientException: {}", uri);
	} catch (Exception e) {
		// e.printStackTrace();
	}
	return result;
}

	public static ResponseEntity<String> callApiGet(String uri, JsonObject body, HttpHeaders httpHeaders){
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> result = null;
		try {
			result = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

		} catch (RestClientException e) {
			//log.debug("RestClientException: {}", uri);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return result;
	}
	
//Get 방식
	public static ResponseEntity<String> callApiGetTest(String uri, JsonObject body, HttpHeaders httpHeaders){
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}
 
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
                //.setMaxConnTotal(50)
                .build();
 
        requestFactory.setHttpClient(httpClient);

		HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);																																																																																																																																																															
		
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

	ResponseEntity<String> result = null;
	try {

		result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);


	} catch (RestClientException e) {
		//log.debug("RestClientException: {}", uri);
	} catch (Exception e) {
		// e.printStackTrace();
	}
	return result;
}
	
	
public static String getRequestStr(HttpServletRequest httpServletRequest) throws IOException{
	StringBuffer sb = new StringBuffer();
	BufferedReader bufferedReader = null;
	try {
		bufferedReader = httpServletRequest.getReader();
		char[] charBuffer = new char[128];
		int bytesRead;
		while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
			sb.append(charBuffer, 0, bytesRead);
		}
	} catch (IOException ex) {
		throw ex;
	} finally {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException ex) {
				throw ex;
			}
		}
	}
	return sb.toString();
}
	
public static String convSTRtoNull(Object obj) {
      if(obj==null) {
         return "";
      }else {
         return (String)obj;
      } 
      
   }
}
