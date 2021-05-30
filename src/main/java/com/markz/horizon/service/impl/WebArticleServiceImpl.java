package com.markz.horizon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markz.horizon.entity.base.BaseResponse;


import com.markz.horizon.entity.dao.Useraccount;
import com.markz.horizon.entity.dao.Webarticle;
import com.markz.horizon.entity.model.ArticlePagingModel;
import com.markz.horizon.entity.model.WebDeployArticleModel;


import com.markz.horizon.entity.model.WebGetUserArticleModel;
import com.markz.horizon.entity.model.WebUserNameModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.mapper.WebarticleMapper;
import com.markz.horizon.service.WebArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class WebArticleServiceImpl implements WebArticleService {

    @Autowired
    WebarticleMapper webarticleMapper;

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String DEPLOYOK = "Deploy OK";
    private final static String DEPLOYFAILED = "Deploy failed";
    private final static String GETUSERARTICLEOK = "Get user article OK";
    private final static String GETUSERARTICLEFAILED = "GET user article failed";
    private final static String GETARTICLEOK = "Get article OK";
    private final static String GETARTICLEFAILED = "GET article failed";
    private final static Integer SUCCESSSTATUS = 0;
    private final static Integer FAILEDTATUS = 1;



    @Override
    public BaseResponse deployArticle(WebDeployArticleModel webDeployArticleModel){
        BaseResponse baseResponse = new BaseResponse();
        Useraccount useraccount = useraccountMapper.selectByPrimaryKey(webDeployArticleModel.getUserName());
        if(useraccount != null){
            Webarticle webarticle = new Webarticle();
            Date date = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String dateString = df.format(new Date());

            try {
                date = df.parse(dateString);
            } catch (ParseException e){
                e.printStackTrace();
            }

            webarticle.setArticle(webDeployArticleModel.getArticle());
            webarticle.setTitle(webDeployArticleModel.getTitle());
            webarticle.setTime(date);
            webarticle.setUsername(webDeployArticleModel.getUserName());
            webarticle.setAvatarurl(useraccount.getAvatarurl());
            webarticle.setNickname(useraccount.getNickname());
            webarticle.setGood(0);
            webarticle.setDislike(0);

            webarticleMapper.insert(webarticle);

            baseResponse.setMessage(DEPLOYOK);
            baseResponse.setStatus(SUCCESSSTATUS);
        }else {
            baseResponse.setMessage(DEPLOYFAILED);
            baseResponse.setStatus(FAILEDTATUS);
        }
        return baseResponse;
    }

    /**
     * 获取用户文章
     * @param webGetUserArticleModel
     * @return
     */
    @Override
    public BaseResponse webGetUserArticle (WebGetUserArticleModel webGetUserArticleModel){
        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(webGetUserArticleModel.getUserName()) != null){
            List<Webarticle> articles = webarticleMapper.selectByUserName(webGetUserArticleModel.getUserName());
            Collections.reverse(articles);
            baseResponse.setStatus(SUCCESSSTATUS);
            baseResponse.setMessage(GETUSERARTICLEOK);

            baseResponse.setData(articles);
        } else {
            baseResponse.setStatus(FAILEDTATUS);
            baseResponse.setMessage(GETUSERARTICLEFAILED);
        }
        return baseResponse;
    }

    /**
     * 获取首页文章
     * @param articlePagingModel
     * @return
     */
    @Override
    public BaseResponse webGetArticle (ArticlePagingModel articlePagingModel){

        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(articlePagingModel.getUserName()) != null){
            PageHelper.startPage(articlePagingModel.getPageNum(),10);
            PageHelper.orderBy("time desc");
            List<Webarticle> webarticles = webarticleMapper.selectAll();
            PageInfo pageInfo = new PageInfo(webarticles);
            System.out.println(pageInfo);
            Map<String,Object> data = new HashMap<String, Object>();

            baseResponse.setStatus(SUCCESSSTATUS);
            baseResponse.setMessage(GETARTICLEOK);
            baseResponse.setData(pageInfo);
        } else {

            baseResponse.setMessage(GETARTICLEFAILED);
            baseResponse.setStatus(FAILEDTATUS);
        }
        return baseResponse;
    }
}
