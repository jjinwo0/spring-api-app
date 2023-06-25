package com.app.api.member.controller;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.api.member.service.MemberInfoService;
import com.app.domain.member.entity.Member;
import com.app.global.jwt.service.TokenManager;
import com.app.global.resolver.memberinfo.MemberInfo;
import com.app.global.resolver.memberinfo.MemberInfoDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberInfoController {

    private final TokenManager tokenManager;
    private final MemberInfoService memberInfoService;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(
//            @RequestHeader("Authorization") String authorizationHeader
            @MemberInfo MemberInfoDto memberInfoDto
            ){
//        String accessToken = authorizationHeader.split(" ")[1];
//        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
//        Long id = Long.valueOf((Integer) tokenClaims.get("id"));
        Long id = memberInfoDto.getId();

        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(id);

        return ResponseEntity.ok(memberInfoResponseDto);
    }
}
