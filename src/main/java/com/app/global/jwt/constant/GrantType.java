package com.app.global.jwt.constant;

import lombok.Getter;

@Getter
public enum GrantType { // 인증 방식 명시

    BEARER("Bearer"); // BEARER: JWT or OAuth token을 사용한다는 의미

    GrantType(String type){
        this.type = type;
    }

    private String type;
}
