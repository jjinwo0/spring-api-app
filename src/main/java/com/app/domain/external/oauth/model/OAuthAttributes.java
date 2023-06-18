package com.app.domain.external.oauth.model;

import com.app.domain.member.constant.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String profile;
    private MemberType memberType;
}
