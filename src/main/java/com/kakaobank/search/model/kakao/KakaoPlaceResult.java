package com.kakaobank.search.model.kakao;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakaobank.search.model.searchplace.KakaoSearchPlaceResult;
import com.kakaobank.search.model.searchplace.KakaoSearchPlaceResultData;

import lombok.Getter;
import lombok.Setter;

/**
 * KAKAO 장소 검색 결과
 * @author JJY
 *
 */
@Getter
@Setter
public class KakaoPlaceResult implements KakaoSearchPlaceResult {
	public KakaoPlaceResultMeta meta;
    
	public List<KakaoPlaceResultDocuments> documents;

    private List<KakaoSearchPlaceResultData> data;
 
    @Override
	public List<KakaoSearchPlaceResultData> getData(){
    	if(data==null) {
    		data = new ArrayList<KakaoSearchPlaceResultData>();
    		for(KakaoPlaceResultDocuments document : this.documents) {
    			data.add(document);
    		}
    	}
		return data;
	}

    /**
     * 분석정보
     *
     */
	@Getter
	@Setter
	public class SameName{
		private String[] region;
		private String keyword;
		private String selected_region;
	}
	
	/**
	 * 검색결과 META
	 *
	 */
	@Getter
	@Setter
	public class KakaoPlaceResultMeta {
		private SameName same_name;
		private int pageable_count;
		private int total_count;
		private boolean is_end;
	}
	/**
	 * 검색 결과
	 *
	 */
	@Getter
	@Setter
	public class KakaoPlaceResultDocuments implements KakaoSearchPlaceResultData{
		private String place_name;
		private String place_url;
		private String address_name;
		private String road_address_name;
		private String phone;
		private String category_group_name;
		private Double x;
		private Double y;
		private String id;
		private String distance;
		private String category_name;
		private String category_group_code;
		
		@Override
		public String getPlace_id() {
			return this.id;
		}
		@Override
		public String getPlace_name() {
			return this.place_name;
		}
		@Override
		public String getPlace_map_link() {
			if(null==this.id) return null;
			return "https://map.kakao.com/link/map/"+this.id;
		}
		@Override
		public String getCategory_group_name() {
			return this.category_group_name;
		}  
		@Override
		public String getRoad_address_name() {
			return this.road_address_name;
		}
		@Override
		public String getAddress_name() {
			return this.address_name;
		}
		@Override
		public String getPhone() {
			return this.phone;
		}
		@Override
		public Double getX() {
			return this.x;
		}
		@Override
		public Double getY() {
			return this.y;
		}
	}
}