package com.app.domain.external.oauth.kakao.service;

import com.app.domain.external.oauth.kakao.client.KakaoUserInfoClient;
import com.app.domain.external.oauth.kakao.dto.KakaoUserInfoResponseDto;
import com.app.domain.external.oauth.model.OAuthAttributes;
import com.app.domain.external.oauth.service.SocialLoginApiService;
import com.app.domain.member.constant.MemberType;
import com.app.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        // AccessToken 앞에 헤더로 BEARER 담아 보내기
        // User 정보 조회
        KakaoUserInfoResponseDto kakaoUserInfoResponseDto = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken);

        // 조회한 User 정보에서 Email 정보 추출
        KakaoUserInfoResponseDto.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
        String email = kakaoAccount.getEmail();

        return OAuthAttributes.builder()
                // email은 입력 필수값으로 지정할 수 없었기 때문에, email이 담겨왔는지 먼저 체크
                // email이 없으면 id값을 저장할 수 있도록 설계
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email)
                .name(kakaoAccount.getProfile().getNickname())
                .profile(kakaoAccount.getProfile().getThumbnailImageUrl())
                .memberType(MemberType.KAKAO)
                .build();
    }
}
