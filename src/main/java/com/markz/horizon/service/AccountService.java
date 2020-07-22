package com.markz.horizon.service;

import com.markz.horizon.entity.VO.LoginParams;
import com.markz.horizon.entity.base.BaseResponse;

import javax.validation.constraints.NotNull;

public interface AccountService {

    @NotNull
    BaseResponse loginService(@NotNull LoginParams loginParams);
}
