package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.*;


import javax.validation.constraints.NotNull;

public interface WebArticleService {
    @NotNull
    BaseResponse deployArticle(@NotNull WebDeployArticleModel webDeployArticleModel);

    @NotNull
    BaseResponse webGetUserArticle(@NotNull WebGetUserArticleModel webGetUserArticleModel);

    @NotNull
    BaseResponse webGetArticle(@NotNull ArticlePagingModel articlePagingModel);

    @NotNull
    BaseResponse webGetUserSupportStatus(@NotNull WebUserNameArticleIdModel webUserNameArticleIdModel);

    @NotNull
    BaseResponse webSupportArticle(@NotNull WebUserNameArticleIdModel webUserNameArticleIdModel);

    @NotNull
    BaseResponse webDislikeArticle(@NotNull WebUserNameArticleIdModel webUserNameArticleIdModel);

    @NotNull
    BaseResponse webCancelSupport(@NotNull WebUserNameArticleIdModel webUserNameArticleIdModel);

    @NotNull
    BaseResponse webCancelDislike(@NotNull WebUserNameArticleIdModel webUserNameArticleIdModel);
}
