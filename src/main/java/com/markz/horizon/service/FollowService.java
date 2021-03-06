package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WebSelfOtherUserNameModel;
import com.markz.horizon.entity.model.WebUserNameModel;

import javax.validation.constraints.NotNull;

public interface FollowService {
    @NotNull
    BaseResponse followUser(@NotNull WebSelfOtherUserNameModel webSelfOtherUserNameModel);

    @NotNull
    BaseResponse isFollowJudge(@NotNull WebSelfOtherUserNameModel webSelfOtherUserNameModel);

    @NotNull
    BaseResponse cancelFollowUser(@NotNull WebSelfOtherUserNameModel webSelfOtherUserNameModel);

    @NotNull
    BaseResponse getFollowUserList(@NotNull WebUserNameModel webUserNameModel);

}
