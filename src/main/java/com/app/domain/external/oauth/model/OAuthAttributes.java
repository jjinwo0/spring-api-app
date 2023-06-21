package com.app.domain.external.oauth.model;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role){
        // OAuthAttributes에 있는 정보를 담아 Member 객체로 만든 후, 해당 객체로 회원가입 진행
        return Member.builder()
                .memberType(memberType)
                .memberName(name)
                .email(email)
                .profile(profile)
                .role(role)
                .build();
    }
}
