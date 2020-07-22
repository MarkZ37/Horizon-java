package com.markz.horizon.controller.api;

import com.markz.horizon.entity.VO.LoginParams;
import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.service.impl.AccountServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid LoginParams loginParams) {
        return accountServiceImpl.loginService(loginParams);
    }
}
