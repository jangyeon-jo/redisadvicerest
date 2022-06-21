package com.kakaobank.search.controller;

import com.kakaobank.search.service.kakao.KakaoSearchPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SimpleCorsFilter  {

    @Autowired
    KakaoSearchPlaceService kakaoSearchPlaceService;

    @GetMapping("/test")
    public String init(@RequestParam String name) {
        return name;
    }

}
