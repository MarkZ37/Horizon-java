package com.markz.horizon.service.impl;


import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.dao.Article;
import com.markz.horizon.entity.model.DeployArticleModel;
import com.markz.horizon.entity.model.OpenIdModel;
import com.markz.horizon.mapper.ArticleMapper;
import com.markz.horizon.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    private final static String UPLOADSUCCESS = "上传成功";
    private final static Integer UPLOADSUCCESSSTATUS = 0;
    private final static String UPLOADFAILED = "上传失败";
    private final static Integer UPLOADFAILEDSTATUS = 1;
    private final static String GETSUCCESS = "获取成功";
    private final static Integer GETSUCCESSSTATUS = 0;
    private final static Integer GETFAILEDTATUS = 1;
    /**
     * 上传文章
     * @param deployArticleModel
     * @return
     */
    @Override
    public BaseResponse uploadArticle(@NotNull DeployArticleModel deployArticleModel){
        Article article = new Article();
        BaseResponse baseResponse = new BaseResponse();
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String dateString = df.format(new Date());

        try {
            date = df.parse(dateString);
        } catch (ParseException e){
            e.printStackTrace();
        }
        article.setOpenid(deployArticleModel.getOpenid());
        article.setArticle(deployArticleModel.getArticle());
        article.setNickname(deployArticleModel.getNickname());
        article.setAvatarurl(deployArticleModel.getAvatarurl());
        article.setTime(date);
        //回复状态
        if(articleMapper.insert(article) > 0){
            baseResponse.setStatus(UPLOADSUCCESSSTATUS);
            baseResponse.setMessage(UPLOADSUCCESS);
        }else {
            baseResponse.setStatus(UPLOADFAILEDSTATUS);
            baseResponse.setMessage(UPLOADFAILED);
        }
        return baseResponse;
    }

    /**
     * 获取用户的文章
     * @param openIdModel
     * @return
     */
    @Override
    public BaseResponse getArticle(OpenIdModel openIdModel){
//        List<Article> articles = articleMapper.selectByOpenId(openIdModel.getOpenid());
//        Collections.reverse(articles);
        BaseResponse baseResponse = new BaseResponse();
//        Map<String,Object> articleMap = new HashMap<String, Object>();
//        baseResponse.setStatus(GETSUCCESSSTATUS);
//        baseResponse.setMessage(GETSUCCESS);
//        baseResponse.setData(articles);
        return baseResponse;
    }

    /**
     * 获取二十篇文章
     * @return
     */
    @Override
    public BaseResponse getMain(){
//        List<Article> articles = articleMapper.selectMain();
//        Collections.reverse(articles);
        BaseResponse baseResponse = new BaseResponse();
//        Map<String,Object> articleMap = new HashMap<String, Object>();
//        baseResponse.setStatus(GETSUCCESSSTATUS);
//        baseResponse.setMessage(GETSUCCESS);
//        baseResponse.setData(articles);
        return baseResponse;
    }
}
