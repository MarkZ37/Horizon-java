package com.markz.horizon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markz.horizon.entity.base.BaseResponse;


import com.markz.horizon.entity.dao.Support;
import com.markz.horizon.entity.dao.Useraccount;
import com.markz.horizon.entity.dao.Webarticle;
import com.markz.horizon.entity.model.*;


import com.markz.horizon.mapper.SupportMapper;
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

    @Autowired
    SupportMapper supportMapper;

    private final static String DEPLOYOK = "Deploy OK";
    private final static String DEPLOYFAILED = "Deploy failed";
    private final static String GETUSERARTICLEOK = "Get user article OK";
    private final static String GETUSERARTICLEFAILED = "GET user article failed";
    private final static String GETARTICLEOK = "Get article OK";
    private final static String GETARTICLEFAILED = "GET article failed";
    private final static String GETSUPPORTSTATUSOK = "Get support status OK";
    private final static String GETSUPPORTSTATUSFAILED = "GET support status failed";
    private final static String SUPPORTOK = "Support OK";
    private final static String SUPPORTFAILED = "Support failed";
    private final static String DISLIKEOK = "Dislike OK";
    private final static String DISLIKEFAILED = "Dislike failed";
    private final static String CANCELSUPPORTOK = "Cancel support OK";
    private final static String CANCELSUPPORTFAILED = "Cancel support failed";
    private final static String CANCELDISLIKEOK = "Cancel dislike OK";
    private final static String CANCELDISLIKEFAILED = "Cancel dislike failed";
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
     * 获取用户的文章
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
            PageHelper.orderBy("good desc");
            List<Webarticle> webarticles = webarticleMapper.selectAll();
            PageInfo pageInfo = new PageInfo(webarticles);

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

    @Override
    public BaseResponse webGetUserSupportStatus (WebUserNameArticleIdModel webUserNameArticleIdModel){
        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(webUserNameArticleIdModel.getUserName()) != null){
            Support support = new Support();
            support.setUsername(webUserNameArticleIdModel.getUserName());
            support.setArticleid(webUserNameArticleIdModel.getArticleId());
            Support result = supportMapper.selectByUserNameAndId(support);
            baseResponse.setMessage(GETSUPPORTSTATUSOK);
            baseResponse.setStatus(SUCCESSSTATUS);
            baseResponse.setData(result);
        }else {
            baseResponse.setMessage(GETSUPPORTSTATUSFAILED);
            baseResponse.setStatus(FAILEDTATUS);

        }
        return baseResponse;
    }

    /**
     * 点赞文章
     * @param webUserNameArticleIdModel
     * @return
     */
    @Override
    public BaseResponse webSupportArticle(WebUserNameArticleIdModel webUserNameArticleIdModel){
        BaseResponse baseResponse = new BaseResponse();

        if (useraccountMapper.selectByPrimaryKey(webUserNameArticleIdModel.getUserName()) != null){
            Webarticle webarticle = webarticleMapper.selectByPrimaryKey(webUserNameArticleIdModel.getArticleId());
            if (webarticle != null){
                //添加点赞量
                Integer supportNum = webarticle.getGood();
                supportNum += 1;
                webarticle.setGood(supportNum);
                webarticleMapper.updateByPrimaryKey(webarticle);
                //添加点赞信息
                Support support = new Support();
                support.setUsername(webUserNameArticleIdModel.getUserName());
                support.setArticleid(webUserNameArticleIdModel.getArticleId());
                Byte status = 1;
                support.setStatus(status);
                if (supportMapper.selectByUserNameAndId(support) != null){
                    supportMapper.updateByPrimaryKey(support);
                }else {
                    supportMapper.insert(support);
                }

                baseResponse.setStatus(SUCCESSSTATUS);
                baseResponse.setMessage(SUPPORTOK);

            }else {
                baseResponse.setStatus(FAILEDTATUS);
                baseResponse.setMessage(SUPPORTFAILED);
            }
        }else {
            baseResponse.setStatus(FAILEDTATUS);
            baseResponse.setMessage(SUPPORTFAILED);
        }

        return baseResponse;
    }

    /**
     * 踩文章
     * @param webUserNameArticleIdModel
     * @return
     */
    @Override
    public BaseResponse webDislikeArticle(WebUserNameArticleIdModel webUserNameArticleIdModel){
        BaseResponse baseResponse = new BaseResponse();

        if (useraccountMapper.selectByPrimaryKey(webUserNameArticleIdModel.getUserName()) != null){
            Webarticle webarticle = webarticleMapper.selectByPrimaryKey(webUserNameArticleIdModel.getArticleId());
            if (webarticle != null){
                //添加点赞量
                Integer dislikeNum = webarticle.getDislike();
                dislikeNum += 1;
                webarticle.setDislike(dislikeNum);
                webarticleMapper.updateByPrimaryKey(webarticle);
                //添加点赞信息
                Support support = new Support();
                support.setUsername(webUserNameArticleIdModel.getUserName());
                support.setArticleid(webUserNameArticleIdModel.getArticleId());
                Byte status = 0;
                support.setStatus(status);
                if (supportMapper.selectByUserNameAndId(support) != null){
                    supportMapper.updateByPrimaryKey(support);
                }else {
                    supportMapper.insert(support);
                }

                baseResponse.setStatus(SUCCESSSTATUS);
                baseResponse.setMessage(DISLIKEOK);

            }else {
                baseResponse.setStatus(FAILEDTATUS);
                baseResponse.setMessage(DISLIKEFAILED);
            }
        }else {
            baseResponse.setStatus(FAILEDTATUS);
            baseResponse.setMessage(DISLIKEFAILED);
        }

        return baseResponse;
    }

    /**
     * 取消赞
     * @param webUserNameArticleIdModel
     * @return
     */
    @Override
    public BaseResponse webCancelSupport(WebUserNameArticleIdModel webUserNameArticleIdModel){
        BaseResponse baseResponse = new BaseResponse();

        Support support = new Support();
        support.setArticleid(webUserNameArticleIdModel.getArticleId());
        support.setUsername(webUserNameArticleIdModel.getUserName());
        Support result = supportMapper.selectByUserNameAndId(support);

        Webarticle webarticle = webarticleMapper.selectByPrimaryKey(webUserNameArticleIdModel.getArticleId());
        if (useraccountMapper.selectByPrimaryKey(webUserNameArticleIdModel.getUserName()) != null
            && webarticle != null
            && result != null){
            if (supportMapper.deleteByPrimaryKey(result.getId()) > 0){
                Integer supportNum = webarticle.getGood();
                supportNum -= 1;
                webarticle.setGood(supportNum);
                if (webarticleMapper.updateByPrimaryKey(webarticle) > 0){
                    baseResponse.setMessage(CANCELSUPPORTOK);
                    baseResponse.setStatus(SUCCESSSTATUS);
                }else {
                    baseResponse.setMessage(CANCELSUPPORTFAILED);
                    baseResponse.setStatus(FAILEDTATUS);
                }

            } else {
                baseResponse.setMessage(CANCELSUPPORTFAILED);
                baseResponse.setStatus(FAILEDTATUS);
            }
        }else {
            baseResponse.setMessage(CANCELSUPPORTFAILED);
            baseResponse.setStatus(FAILEDTATUS);
        }

        return baseResponse;
    }

    /**
     * 取消踩
     * @param webUserNameArticleIdModel
     * @return
     */
    @Override
    public BaseResponse webCancelDislike(WebUserNameArticleIdModel webUserNameArticleIdModel){
        BaseResponse baseResponse = new BaseResponse();

        Support support = new Support();
        support.setArticleid(webUserNameArticleIdModel.getArticleId());
        support.setUsername(webUserNameArticleIdModel.getUserName());
        Support result = supportMapper.selectByUserNameAndId(support);

        Webarticle webarticle = webarticleMapper.selectByPrimaryKey(webUserNameArticleIdModel.getArticleId());
        if (useraccountMapper.selectByPrimaryKey(webUserNameArticleIdModel.getUserName()) != null
                && webarticle != null
                && result != null){
            if (supportMapper.deleteByPrimaryKey(result.getId()) > 0){
                Integer dislikeNum = webarticle.getDislike();
                dislikeNum -= 1;
                webarticle.setDislike(dislikeNum);
                if (webarticleMapper.updateByPrimaryKey(webarticle) > 0){
                    baseResponse.setMessage(CANCELDISLIKEOK);
                    baseResponse.setStatus(SUCCESSSTATUS);
                }else {
                    baseResponse.setMessage(CANCELDISLIKEFAILED);
                    baseResponse.setStatus(FAILEDTATUS);
                }

            } else {
                baseResponse.setMessage(CANCELDISLIKEFAILED);
                baseResponse.setStatus(FAILEDTATUS);
            }
        }else {
            baseResponse.setMessage(CANCELDISLIKEFAILED);
            baseResponse.setStatus(FAILEDTATUS);
        }

        return baseResponse;
    }
}
