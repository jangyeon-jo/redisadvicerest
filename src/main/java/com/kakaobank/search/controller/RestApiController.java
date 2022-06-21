package com.kakaobank.search.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakaobank.search.model.BestKeywordResult;
import com.kakaobank.search.model.kakao.KakaoPlaceResult;
import com.kakaobank.search.model.naver.NaverPlaceResult;
import com.kakaobank.search.service.KeywordHistoryService;
import com.kakaobank.search.service.ResponseService;
import com.kakaobank.search.service.SearchResultService;
import com.kakaobank.search.service.SortKeyWordService;
import com.kakaobank.search.service.kakao.KakaoSearchPlaceService;
import com.kakaobank.search.service.naver.NaverSearchPlaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;

@Api(tags = { "1. Search Swagger Test" })
@Slf4j
@RestController
@RequestMapping("/v1/search")
public class RestApiController {

	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Autowired
	KakaoSearchPlaceService kakaoSearchPlaceService;
	
	@Autowired
	NaverSearchPlaceService naverSearchPlaceService;
	
	@Autowired
	SortKeyWordService sortKeyWordService;
	
	@Autowired
	SearchResultService searchResultService;

	@Autowired
	KeywordHistoryService keywordHistoryService;
	
	@Autowired
	ResponseService responseService;
	
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, Object> valueOperation;
	
	  
    //카카오 API 네이버 API 정보 가져오
	@ApiOperation(value="장소검색 함수", notes="Kakao, Naver 장소 API이용 하여 값 Sorting 후 결과값을 보여줌.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "vendor", value = "검색엔진(kakao or naver or all)", required = true, dataType = "string", paramType = "path", defaultValue = ""),
        @ApiImplicitParam(name = "query", value = "검색키", required = true, dataType = "string", paramType = "path", defaultValue = "")
	})
    @GetMapping("/place/{vendor}")
    public Object searchRestApi(@PathVariable("vendor") String vendor, HttpServletResponse response, @RequestParam(defaultValue="곱창") String query) {

    	if (query.equals("")) {
    		throw new RuntimeException("사용자 파라미터 오류");
			
		}
    	
    	//헤더 값 설정.
        HttpHeaders httpHeaderKakao =  makeCommonHeader();	
        HttpHeaders httpHeaderNaver =  makeCommonHeader();	
        
        //API호출 값 초기.
        ResponseEntity<String> resultKakao = null;
        ResponseEntity<String> resultNaver = null;
        
        //파싱 값 초기화.
        KakaoPlaceResult listKakao = null;	
    	NaverPlaceResult listNaver = null;
    	
    	//최종 결과 값 전달.
    	List<String> result = null;
    	
    	//Gson 이용하여 파싱진행.
    	Gson gson = new Gson();
 
    	//Redis에 키값 저장.
    	searchResultService.writeKeyword(query);
    	
        //카카오 API, 네이버 API 호출
        if (vendor.equals("kakao") || vendor.equals("all") ) {
        	resultKakao = kakaoSearchPlaceService.callApi(httpHeaderKakao, query);	
        	
		} 
        if (vendor.equals("naver") || vendor.equals("all")) {
        	resultNaver = naverSearchPlaceService.callApi(httpHeaderNaver, query);
        	
		}
        
        //카카오 API, 네이버 API 값을 Gson으로 파싱진행.
		if (resultKakao != null) {
			listKakao = gson.fromJson(resultKakao.getBody(), KakaoPlaceResult.class);	
			
		} 
		if (resultNaver != null)  {
			listNaver = gson.fromJson(resultNaver.getBody(), NaverPlaceResult.class);
			
		}
        
		//카카오, 네이버,전체 검색할 수 있게 진행.
		if (vendor.equals("kakao"))  {
			result = sortKeyWordService.kakaoSort(listKakao.getDocuments());
			
		} else if(vendor.equals("naver")){ 
			result = sortKeyWordService.naverSort(listNaver.getItems());
			
		} else {
			result = sortKeyWordService.kakaoNaverSort(listKakao.getDocuments(), listNaver.getItems());
			
		}

    	response.setStatus(HttpServletResponse.SC_OK);

    	return responseService.getListResult(result);
    }
    
    //카카오 API 네이버 API 정보 가져오
	@ApiOperation(value="많이 검색한 키워드", notes="사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다")
    @GetMapping("/place/bestkeyword")
    public Object bestKeyWord(HttpServletResponse response) {
    	
    	List<BestKeywordResult> result = keywordHistoryService.bestKeywordCountAll();
    	response.setStatus(HttpServletResponse.SC_OK);

    	return responseService.getListResult(result);
    }
    
    private HttpHeaders makeCommonHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept",MediaType.APPLICATION_JSON_VALUE); 
	    headers.add("Charset", "UTF-8");
		return headers;
	}
}



