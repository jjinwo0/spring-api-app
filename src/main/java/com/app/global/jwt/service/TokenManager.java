package com.app.global.jwt.service;

import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.constant.GrantType;
import com.app.global.jwt.constant.TokenType;
import com.app.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String tokenSecret; // token 생성을 위한 secret key

    public JwtTokenDto createJwtTokenDto(Long id, Role role){
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(id, role, accessTokenExpireTime);
        String refreshToken = createRefreshToken(id, refreshTokenExpireTime);
        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    public Date createAccessTokenExpireTime(){
        // 현재시간 + 15min
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime(){
        // 현재시간 + 2week
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(Long id, Role role, Date expirationTime){
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())                // token의 제목
                .setIssuedAt(new Date(System.currentTimeMillis()))  // token이 생성된 시간 (현재 시간)
                .setExpiration(expirationTime)                      // 만료 시간
                .claim("id", id)                              // 회원 id
                .claim("role", role)                          // 사용자 역할
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return accessToken;
    }

    public String createRefreshToken(Long id, Date expirationTime){
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())               // token의 제목
                .setIssuedAt(new Date())                            // token이 생성된 시간
                .setExpiration(expirationTime)                      // 만료 시간
                .claim("id", id)                               // 사용자 역할
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("type", "JWT")
                .compact();

        return refreshToken;
    }

    // token 검증
    public void validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8)) // 토큰 생성 시 사용했던 secret key
                    .parseClaimsJws(token); // token parsing
        } catch (ExpiredJwtException e){
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    public Claims getTokenClaims(String token){
        Claims claims;

        try {
            claims = Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e){
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }

        return claims;
    }
}
