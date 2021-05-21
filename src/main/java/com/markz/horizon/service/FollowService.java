package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WebSelfOtherUserNameModel;

import javax.validation.constraints.NotNull;

public interface FollowService {
    @NotNull
    BaseResponse followUser(@NotNull WebSelfOtherUserNameModel webSelfOtherUserNameModel);
}
