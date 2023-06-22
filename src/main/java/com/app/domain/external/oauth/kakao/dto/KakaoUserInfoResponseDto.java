package com.app.domain.external.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoUserInfoResponseDto {

    private String id;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    /**
     * static inner class의 사용
     * Request, Response 각각에 대한 DTO를 따로 생성하지 않고 inner class로 관리
     */
    @Getter @Setter
    public static class KakaoAccount{
        private String email;
        private Profile profile;

        @Getter @Setter
        public static class Profile {
            private String nickname;
            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;
        }
    }
}
