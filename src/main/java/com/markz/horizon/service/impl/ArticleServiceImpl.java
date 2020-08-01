package com.markz.horizon.service.impl;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.DeployArticleModel;
import com.markz.horizon.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Override
    public BaseResponse uploadArticle(@NotNull DeployArticleModel deployArticleModel){
        System.out.println(deployArticleModel);
        BaseResponse baseResponse = new BaseResponse();
        return baseResponse;
    }
}
