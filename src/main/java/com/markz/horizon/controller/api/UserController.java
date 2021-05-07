package com.markz.horizon.controller.api;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.entity.model.WebGetUserInfoModel;
import com.markz.horizon.entity.model.WebLoginModel;
import com.markz.horizon.entity.model.WebRegistModel;
import com.markz.horizon.service.WeChatLoginService;
import com.markz.horizon.service.WebLoginService;
import com.markz.horizon.service.WebRegistService;
import com.markz.horizon.service.WebUserService;
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

    WeChatLoginService weChatLoginService;

    @Autowired
    WebLoginService webLoginService;

    @Autowired
    WebRegistService webRegistService;

    @Autowired
    WebUserService webUserService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid WeChatLoginModel weChatLoginModel) {
        return weChatLoginService.loginService(weChatLoginModel);
    }

    @ApiOperation("网页用户登录")
    @PostMapping("/weblogin")
    public BaseResponse webLogin(@RequestBody @Valid WebLoginModel webLoginModel) {
        return webLoginService.webLoginService(webLoginModel);
    }

    @ApiOperation("网页用户注册")
    @PostMapping("/webregist")
    public BaseResponse webRegist(@RequestBody @Valid WebRegistModel webRegistModel) {
        return webRegistService.webRegistService(webRegistModel);
    }

    @ApiOperation("网页用户获取用户基础信息")
    @PostMapping("/webgetuserinfo")
    public BaseResponse webGetUserInfo(@RequestBody @Valid WebGetUserInfoModel webGetUserInfoModel) {
        return webUserService.webGetUserInfo(webGetUserInfoModel);
    }
}
