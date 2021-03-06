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
	
	  
    //????????? API ????????? API ?????? ?????????
	@ApiOperation(value="???????????? ??????", notes="Kakao, Naver ?????? API?????? ?????? ??? Sorting ??? ???????????? ?????????.")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "vendor", value = "????????????(kakao or naver or all)", required = true, dataType = "string", paramType = "path", defaultValue = ""),
        @ApiImplicitParam(name = "query", value = "?????????", required = true, dataType = "string", paramType = "path", defaultValue = "")
	})
    @GetMapping("/place/{vendor}")
    public Object searchRestApi(@PathVariable("vendor") String vendor, HttpServletResponse response, @RequestParam(defaultValue="??????") String query) {

    	if (query.equals("")) {
    		throw new RuntimeException("????????? ???????????? ??????");
			
		}
    	
    	//?????? ??? ??????.
        HttpHeaders httpHeaderKakao =  makeCommonHeader();	
        HttpHeaders httpHeaderNaver =  makeCommonHeader();	
        
        //API?????? ??? ??????.
        ResponseEntity<String> resultKakao = null;
        ResponseEntity<String> resultNaver = null;
        
        //?????? ??? ?????????.
        KakaoPlaceResult listKakao = null;	
    	NaverPlaceResult listNaver = null;
    	
    	//?????? ?????? ??? ??????.
    	List<String> result = null;
    	
    	//Gson ???????????? ????????????.
    	Gson gson = new Gson();
 
    	//Redis??? ?????? ??????.
    	searchResultService.writeKeyword(query);
    	
        //????????? API, ????????? API ??????
        if (vendor.equals("kakao") || vendor.equals("all") ) {
        	resultKakao = kakaoSearchPlaceService.callApi(httpHeaderKakao, query);	
        	
		} 
        if (vendor.equals("naver") || vendor.equals("all")) {
        	resultNaver = naverSearchPlaceService.callApi(httpHeaderNaver, query);
        	
		}
        
        //????????? API, ????????? API ?????? Gson?????? ????????????.
		if (resultKakao != null) {
			listKakao = gson.fromJson(resultKakao.getBody(), KakaoPlaceResult.class);	
			
		} 
		if (resultNaver != null)  {
			listNaver = gson.fromJson(resultNaver.getBody(), NaverPlaceResult.class);
			
		}
        
		//?????????, ?????????,?????? ????????? ??? ?????? ??????.
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
    
    //????????? API ????????? API ?????? ?????????
	@ApiOperation(value="?????? ????????? ?????????", notes="??????????????? ?????? ????????? ????????????, ?????? 10?????? ?????? ???????????? ???????????????")
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



