package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.DeployArticleModel;
import com.markz.horizon.entity.model.WeChatLoginModel;

import javax.validation.constraints.NotNull;

public interface ArticleService {

    @NotNull
    BaseResponse uploadArticle(@NotNull DeployArticleModel deployArticleModel);
}
