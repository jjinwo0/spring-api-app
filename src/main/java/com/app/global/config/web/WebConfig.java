package com.app.global.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**") //어떤 url로 요청이 왔을 때 허용할 것인지 작성
                .allowedOrigins("*", "http://localhost:8082") //어떤 Origin을 허용할 것인지
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name()
                ).maxAge(3600); //Access-Control-Max-Age: 3600 으로 설정
        // 접근 제어 시간을 3600ms로 설정
        // default -> 1800
    }
}
