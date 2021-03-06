package com.markz.horizon.service.impl;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.dao.Useraccount;
import com.markz.horizon.entity.dao.Webfollow;
import com.markz.horizon.entity.model.WebSelfOtherUserNameModel;
import com.markz.horizon.entity.model.WebUserNameModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.mapper.WebfollowMapper;
import com.markz.horizon.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {

    @Autowired
    WebfollowMapper webfollowMapper;

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String FOLLOWUSEROK = "Follow user OK";
    private final static String FOLLOWUSERFAILED = "Follow user failed";
    private final static String CANCELFOLLOWUSEROK = "Cancel follow user OK";
    private final static String CANCELFOLLOWUSERFAILED = "Cancel follow user failed";
    private final static String GETFOLLOWLISTOK = "Get follow list OK";
    private final static String GETFOLLOWLISTFAILED = "Get follow list failed";
    private final static String ISFOLLOW = "Have followed";
    private final static String NOTFOLLOW = "Not follow";
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

    @Override
    public @NotNull BaseResponse isFollowJudge(WebSelfOtherUserNameModel webSelfOtherUserNameModel){
        BaseResponse baseResponse = new BaseResponse();
        if (webfollowMapper.selectByUserName(webSelfOtherUserNameModel.getSelfUserName(),webSelfOtherUserNameModel.getOtherUserName()) != null){
            baseResponse.setMessage(ISFOLLOW);
            baseResponse.setStatus(OKSTATUS);
        }else {
            baseResponse.setStatus(FAILEDSTATUS);
            baseResponse.setMessage(NOTFOLLOW);
        }
        return baseResponse;
    }

    @Override
    public @NotNull BaseResponse cancelFollowUser(WebSelfOtherUserNameModel webSelfOtherUserNameModel){
        BaseResponse baseResponse = new BaseResponse();
        if(useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getSelfUserName()) != null
                && useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getOtherUserName()) != null){

            if (webfollowMapper.selectByUserName(webSelfOtherUserNameModel.getSelfUserName(),webSelfOtherUserNameModel.getOtherUserName()) != null){

                webfollowMapper.deletByUserName(webSelfOtherUserNameModel.getSelfUserName(),webSelfOtherUserNameModel.getOtherUserName());
                baseResponse.setStatus(OKSTATUS);
                baseResponse.setMessage(CANCELFOLLOWUSEROK);

            }else {
                baseResponse.setStatus(FAILEDSTATUS);
                baseResponse.setMessage(CANCELFOLLOWUSERFAILED);
            }
        }else {
            baseResponse.setStatus(FAILEDSTATUS);
            baseResponse.setMessage(CANCELFOLLOWUSERFAILED);
        }
        return baseResponse;
    }

    @Override
    public @NotNull BaseResponse getFollowUserList( WebUserNameModel webUserNameModel){
        BaseResponse baseResponse = new BaseResponse();
        if(useraccountMapper.selectByPrimaryKey(webUserNameModel.getUserName()) != null){
            List<Webfollow> webfollowList = webfollowMapper.selectUserFollowList(webUserNameModel.getUserName());
            if (webfollowList != null){

                List<Object> dataList = new ArrayList<Object>();
                for (Webfollow item:webfollowList
                     ) {
                    Useraccount useraccount = useraccountMapper.selectByPrimaryKey(item.getFlusername());
                    if (useraccount != null){
                        Map<String,Object> userInfo = new HashMap<String, Object>();

                        String avatarUrl = useraccount.getAvatarurl();
                        String nickname = useraccount.getNickname();
                        userInfo.put("avatarUrl",avatarUrl);
                        userInfo.put("nickName",nickname);
                        userInfo.put("flUserName",item.getFlusername());
                        dataList.add(userInfo);
                    }
                }

                baseResponse.setMessage(GETFOLLOWLISTOK);
                baseResponse.setStatus(OKSTATUS);
                baseResponse.setData(dataList);
            }else {

                baseResponse.setMessage(GETFOLLOWLISTFAILED);
                baseResponse.setStatus(FAILEDSTATUS);
            }
        }else {

            baseResponse.setMessage(GETFOLLOWLISTFAILED);
            baseResponse.setStatus(FAILEDSTATUS);
        }
        return baseResponse;
    }
}
