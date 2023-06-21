package com.app.domain.external.oauth.service;

import com.app.domain.member.constant.MemberType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    // SocialLoginApiService 구현체가 각각 들어가게 됨
    private static Map<String, SocialLoginApiService> socialLoginApiServices;

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
        this.socialLoginApiServices = socialLoginApiServices;
    }

    public static SocialLoginApiService getSocialLoginApiService(MemberType memberType){
        String socialLoginApiServiceBeanName = "";

        if (MemberType.KAKAO.equals(memberType)){
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }

        return socialLoginApiServices.get(socialLoginApiServiceBeanName);
    }
}
