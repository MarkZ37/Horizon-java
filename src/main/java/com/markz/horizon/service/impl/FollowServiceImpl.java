package com.markz.horizon.service.impl;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.dao.Webfollow;
import com.markz.horizon.entity.model.WebSelfOtherUserNameModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.mapper.WebfollowMapper;
import com.markz.horizon.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {

    @Autowired
    WebfollowMapper webfollowMapper;

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String FOLLOWUSEROK = "Follow user OK";
    private final static String FOLLOWUSERFAILED = "Follow user failed";
    private final static Integer OKSTATUS = 0;
    private final static Integer FAILEDSTATUS = 1;
    @Override
    public @NotNull BaseResponse followUser(WebSelfOtherUserNameModel webSelfOtherUserNameModel){
        BaseResponse baseResponse = new BaseResponse();
        if(useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getSelfUserName()) != null
            && useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getOtherUserName()) != null){

            if (webfollowMapper.selectByUserName(webSelfOtherUserNameModel.getSelfUserName(),webSelfOtherUserNameModel.getOtherUserName()) == null){
                Webfollow webfollow = new Webfollow();
                webfollow.setUsername(webSelfOtherUserNameModel.getSelfUserName());
                webfollow.setFlusername(webSelfOtherUserNameModel.getOtherUserName());
                webfollowMapper.insert(webfollow);
                baseResponse.setStatus(OKSTATUS);
                baseResponse.setMessage(FOLLOWUSEROK);
            }else {
                baseResponse.setStatus(FAILEDSTATUS);
                baseResponse.setMessage(FOLLOWUSERFAILED);
            }
        }else {
            baseResponse.setStatus(FAILEDSTATUS);
            baseResponse.setMessage(FOLLOWUSERFAILED);
        }
        return baseResponse;
    }
}
