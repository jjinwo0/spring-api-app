package com.app.api.exceptiontest.controller;

import com.app.api.exceptiontest.dto.BindExceptionTestDto;
import com.app.api.exceptiontest.dto.TestEnum;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

    @GetMapping("/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDto dto){
        return "ok";
    }

    @GetMapping("/type-exception-test")
    public String bindExceptionTest(TestEnum testEnum){
        return "ok";
    }

    @GetMapping("/business-exception-test")
    public String businessExceptionTest(String isError){
        if (isError.equals("true"))
            throw new BusinessException(ErrorCode.TEST);
        return "ok";
    }
}
