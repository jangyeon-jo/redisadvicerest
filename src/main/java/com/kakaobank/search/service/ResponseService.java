package com.kakaobank.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakaobank.search.repo.CommonResponse;
import com.kakaobank.search.repo.CommonResult;
import com.kakaobank.search.repo.ListResult;
import com.kakaobank.search.repo.SingleResult;


@Service
public class ResponseService {
	   /**
     * 단일건 결과를 처리
     * @param <T>
     * @param data
     * @return
     */
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    /**
     * 다중건 결과를 처리
     * @param <T>
     * @param list
     * @return
     */
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }
    /**
     * 성공 결과 처리
     * @return
     */
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    /**
     * 실패 결과 처리
     * @return
     */
    public CommonResult getFailResult(CommonResponse resp) {
        return getFailResult(resp, null);
    }
    /**
     * 실패 결과 처리
     * @return
     */
    public CommonResult getFailResult(CommonResponse resp, String detailErrorMessage) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(resp.getCode());
        result.setMessage(resp.getMessage());
        result.setDetailErrorMessage(detailErrorMessage);
        return result;
    }
    /**
     * 결과 모델에 api 요청 성공 데이터를 셋팅
     * @param result
     */
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }
}



