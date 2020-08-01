package com.markz.horizon.controller.api;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.service.WeChatLoginService;
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
//    AccountServiceImpl accountServiceImpl;
    WeChatLoginService weChatLoginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid WeChatLoginModel weChatLoginModel) {
        return weChatLoginService.loginService(weChatLoginModel);
    }

}
