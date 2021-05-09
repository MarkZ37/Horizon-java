package com.markz.horizon.service;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WebDeployArticleModel;


import javax.validation.constraints.NotNull;

public interface WebArticleService {
    @NotNull
    BaseResponse deployArticle(@NotNull WebDeployArticleModel webDeployArticleModel);
}
