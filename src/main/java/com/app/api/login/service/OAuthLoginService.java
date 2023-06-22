package com.app.api.login.service;

import com.app.api.login.dto.OAuthLoginDto;
import com.app.domain.external.oauth.model.OAuthAttributes;
import com.app.domain.external.oauth.service.SocialLoginApiService;
import com.app.domain.external.oauth.service.SocialLoginApiServiceFactory;
import com.app.domain.member.constant.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class OAuthLoginService {

    public OAuthLoginDto.Response oauthLogin(String accessToken, MemberType memberType){

        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}", userInfo);

        return OAuthLoginDto.Response.builder().build();
    }
}
