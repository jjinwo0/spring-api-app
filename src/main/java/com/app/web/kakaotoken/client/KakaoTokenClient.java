package com.app.web.kakaotoken.client;

import com.app.web.kakaotoken.dto.KakaoTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenClient")
public interface KakaoTokenClient {

    @PostMapping(value = "/oauth/token", consumes = "application/json")
    // Request에 담긴 내용을 한번에 처리 -> @SpringQueryMap
    KakaoTokenDto.Response requestKakaoToken(@RequestHeader("Content-Type") String contentType,
                                             @SpringQueryMap KakaoTokenDto.Request request);
}
