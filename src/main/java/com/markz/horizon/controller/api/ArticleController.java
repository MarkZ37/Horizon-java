package com.markz.horizon.controller.api;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.DeployArticleModel;
import com.markz.horizon.entity.model.OpenIdModel;
import com.markz.horizon.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/uploadArticle", method = RequestMethod.POST)
    public BaseResponse responseArticle(@RequestBody @Valid DeployArticleModel deployArticleModel){
        return articleService.uploadArticle(deployArticleModel);
    }

    @RequestMapping(value = "/getArticle", method = RequestMethod.POST)
    public BaseResponse getArticle(@RequestBody @Valid OpenIdModel openIdModel){
        return articleService.getArticle(openIdModel);
    }

    @RequestMapping(value = "/getMain", method = RequestMethod.POST)
    public BaseResponse getMain(){
        return articleService.getMain();
    }
}
