package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WebDeployArticleModel;
import com.markz.horizon.entity.model.WebGetUserArticleModel;


import javax.validation.constraints.NotNull;

public interface WebArticleService {
    @NotNull
    BaseResponse deployArticle(@NotNull WebDeployArticleModel webDeployArticleModel);

    @NotNull
    BaseResponse webGetUserArticle(@NotNull WebGetUserArticleModel webGetUserArticleModel);
}
