package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.UploadAvatarUrlModel;
import com.markz.horizon.entity.model.WebGetUserInfoModel;
import com.markz.horizon.entity.model.WebRegistModel;

import javax.validation.constraints.NotNull;

public interface WebUserService {
    @NotNull
    BaseResponse webGetUserInfo(@NotNull WebGetUserInfoModel webGetUserInfoModel);

    @NotNull
    BaseResponse uploadAvatarUrl(@NotNull UploadAvatarUrlModel uploadAvatarUrlModel);
}
