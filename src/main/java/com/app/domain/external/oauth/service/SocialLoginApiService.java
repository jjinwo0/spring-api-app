package com.app.domain.external.oauth.service;

import com.app.domain.external.oauth.model.OAuthAttributes;

public interface SocialLoginApiService { // 소셜 플랫폼에서 회원 정보를 가져오기 위한 인터페이스

    OAuthAttributes getUserInfo(String accessToken);
}
