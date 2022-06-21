package com.kakaobank.search.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakaobank.search.model.kakao.KakaoPlaceResult.KakaoPlaceResultDocuments;
import com.kakaobank.search.model.naver.NaverPlaceResult.NaverPlaceResultDocuments;

@Service
public class SortKeyWordService {
	
	 //카카오와 네이버 비교 하며, 카카오가 우순위로 나오게끔 수정 진
    public List<String> kakaoNaverSort(List<KakaoPlaceResultDocuments> kakaoData, List<NaverPlaceResultDocuments> naverData){
    	List<String> list1 = new LinkedList<String>();
    	List<String> list2 = new LinkedList<String>();
    	List<String> answer = new LinkedList<String>();
    	
    	for (KakaoPlaceResultDocuments x : kakaoData) {
    		list1.add(x.getPlace_name().replaceAll(" ", ""));
		}
    	
    	for (NaverPlaceResultDocuments x : naverData) {
    		list2.add(x.getTitle().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>|\\s", ""));
		}
    	
    	String kakaoName = "";
    	String naverName = "";
    	
    	for (KakaoPlaceResultDocuments x : kakaoData) {
    		kakaoName = x.getPlace_name();
			for (NaverPlaceResultDocuments y : naverData) {
				naverName = y.getTitle();
				if (kakaoName.equals(naverName)) {
					answer.add(kakaoName);
					list1.remove(kakaoName);
					list2.remove(kakaoName);
					break;
				}
			}
		}
    	
    	answer.addAll(list1);
    	answer.addAll(list2);
    	
    	return answer;
    	
    }
    
	 //카카오와 네이버 비교 하며, 카카오가 우순위로 나오게끔 수정 진
    public List<String> kakaoSort(List<KakaoPlaceResultDocuments> kakaoData){
    	List<String> answer = new LinkedList<String>();
    	
    	String kakaoName = "";
    	
    	for (KakaoPlaceResultDocuments x : kakaoData) {
    		kakaoName = x.getPlace_name();
			answer.add(kakaoName);
		}
    	
    	return answer;
    	
    }
    
    
	 //카카오와 네이버 비교 하며, 카카오가 우순위로 나오게끔 수정 진
    public List<String>naverSort(List<NaverPlaceResultDocuments> naverData){
    	List<String> answer = new LinkedList<String>();
    	
    	String naverName = "";
    	
		for (NaverPlaceResultDocuments x : naverData) {
			naverName = x.getTitle();
			answer.add(naverName);
		}
    	
    	return answer;
    	
    }
}
