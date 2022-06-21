package com.kakaobank.search.model.naver;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakaobank.search.model.searchplace.NaverSearchPlaceResult;
import com.kakaobank.search.model.searchplace.NaverSearchPlaceResultData;

import lombok.Getter;
import lombok.Setter;

/**
 * NAVER 장소 검색 결과
 * @author JJY
 *
 */
@Getter
@Setter
public class NaverPlaceResult implements NaverSearchPlaceResult {
	
	private String total;
	private String start;
	private String display;
	
	public List<NaverPlaceResultDocuments> items;

    private List<NaverSearchPlaceResultData> data;
 
    @Override
	public List<NaverSearchPlaceResultData> getData(){
    	if(data==null) {
    		data = new ArrayList<NaverSearchPlaceResultData>();
    		for(NaverPlaceResultDocuments items : this.items) {
    			data.add(items);
    		}
    	}
		return data;
	}


	/**
	 * 검색 결과
	 *
	 */
    @Getter
    @Setter
	public class NaverPlaceResultDocuments implements NaverSearchPlaceResultData{
		private String title;
		private String link;
		private String category;
		private String description;
		private String telephone;
		private String address;
		private String roadAddress;
		private String mapx;
		private String mapy;
		@Override
		public String getTitle() {
			return this.title;
		}
		@Override
		public String getLink() {
			return this.link;
		}
		@Override
		public String getCategory() {
			return this.category;
		}
		@Override
		public String getDescription() {
			return this.description;
		}
		@Override
		public String getTelephone() {
			return this.telephone;
		}
		@Override
		public String getAddress() {
			return this.address;
		}
		@Override
		public String getRoadAddress() {
			return this.roadAddress;
		}
		@Override
		public String getMapx() {
			return this.mapx;
		}
		@Override
		public String getMapy() {
			return this.mapy;
	}
}
}
