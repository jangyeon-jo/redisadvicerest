package com.kakaobank.search.repo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
	private List<T> list;
}
