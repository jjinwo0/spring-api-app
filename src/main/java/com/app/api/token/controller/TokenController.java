package com.app.api.token.controller;

import com.app.api.token.dto.AccessTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenController {

//    @PostMapping("/access-token/issue")
//    public ResponseEntity<AccessTokenResponseDto> createAccessToken(HttpServletRequest httpServletRequest){
//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//    }
}
