package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.entity.model.WebLoginModel;

import javax.validation.constraints.NotNull;

public interface WebLoginService {
    @NotNull
    BaseResponse webLoginService(@NotNull WebLoginModel webLoginModel);
}
