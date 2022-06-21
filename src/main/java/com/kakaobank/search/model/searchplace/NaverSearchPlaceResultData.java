package com.kakaobank.search.model.searchplace;

/**
 * 장소검색 결과 데이터 인터페이스
 * @author JJY
 *
 */
public interface NaverSearchPlaceResultData {
	public String getTitle();
	public String getLink();
	public String getCategory();
	public String getDescription();
	public String getTelephone();
	public String getAddress();
	public String getRoadAddress();
	public String getMapx();
	public String getMapy();
}
