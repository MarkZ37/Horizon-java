package com.markz.horizon.service.impl;

import com.markz.horizon.entity.User;
import com.markz.horizon.entity.VO.LoginParams;
import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.mapper.UserMapper;
import com.markz.horizon.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserMapper userMapper;

    private final static  String PASSERROR = "用户名或密码不正确";

    private final static  String LOGINSUCCESS ="用户登陆成功";

    @Override
    public @NotNull BaseResponse loginService(@NotNull LoginParams loginParams) {
        User user = new User();
        System.out.println(loginParams);
        BaseResponse baseResponse = new BaseResponse();
        user = userMapper.selectByPrimaryKey(Integer.parseInt(loginParams.getAccount()));
        System.out.println(user.getPassword());
        if (user.getPassword().equals(loginParams.getPassword())) {
            baseResponse.setStatus(1);
            baseResponse.setData(user);
            baseResponse.setMessage(LOGINSUCCESS);
        } else {
            baseResponse.setStatus(0);
            baseResponse.setData("用户信息");
            baseResponse.setMessage(PASSERROR);
        }
        return baseResponse;
    }
}
