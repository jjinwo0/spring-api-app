package com.app.api.login.validate;

import com.app.domain.member.constant.MemberType;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.error.exception.BusinessException;
import com.app.global.jwt.constant.GrantType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OAuthValidator {

    public void validateMemberType(String memberType){
        if (!MemberType.isMemberType(memberType))
            throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
    }
}
