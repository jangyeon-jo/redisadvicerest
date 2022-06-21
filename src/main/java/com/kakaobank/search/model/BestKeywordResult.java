package com.kakaobank.search.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BestKeywordResult {
	private int rank;
	private String keyword;
	private Long count;

}