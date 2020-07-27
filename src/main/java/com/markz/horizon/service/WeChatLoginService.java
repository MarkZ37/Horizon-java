package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;

import javax.validation.constraints.NotNull;

public interface WeChatLoginService {

    @NotNull
    BaseResponse loginService(@NotNull WeChatLoginModel weChatLoginModel);
}
