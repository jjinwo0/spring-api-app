package com.app.api.login.controller;

import com.app.api.login.dto.OAuthLoginDto;
import com.app.api.login.service.OAuthLoginService;
import com.app.api.login.validate.OAuthValidator;
import com.app.domain.member.constant.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OAuthLoginController {

    private final OAuthValidator oAuthValidator;
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/login")
    public ResponseEntity<OAuthLoginDto.Response> oauthLogin(@RequestBody OAuthLoginDto.Request oauthLoginRequestDto,
                                                             HttpServletRequest httpServletRequest){ // Header에 대한 토큰 정보를 활용하기 위해 httpServletRequest Param 사용

        String authorizationHeader = httpServletRequest.getHeader("Authorization"); // 인증 관련 정보

        // 헤더 내부 필수값 체크(hasTest & GrantType.BEARER)
        oAuthValidator.validateAuthorization(authorizationHeader);
        oAuthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        String accessToken = authorizationHeader.split(" ")[1];

        oAuthLoginService.oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));

        return ResponseEntity.ok(OAuthLoginDto.Response.builder().build());
    }
}
