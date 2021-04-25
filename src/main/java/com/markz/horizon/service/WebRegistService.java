package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;

import com.markz.horizon.entity.model.WebRegistModel;

import javax.validation.constraints.NotNull;

public interface WebRegistService {
    @NotNull
    BaseResponse webRegistService(@NotNull WebRegistModel webRegistModel);
}
