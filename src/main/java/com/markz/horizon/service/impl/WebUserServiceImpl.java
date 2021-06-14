package com.markz.horizon.service.impl;


import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.dao.Useraccount;
import com.markz.horizon.entity.model.UploadAvatarUrlModel;
import com.markz.horizon.entity.model.WebSelfOtherUserNameModel;
import com.markz.horizon.entity.model.WebGetUserInfoModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.service.WebUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
@Slf4j
public class WebUserServiceImpl implements WebUserService {

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String GETUSERINFOOK = "Get userinfo OK";
    private final static String GETUSERINFOFAILED = "Get userinfo failed";
    private final static String UPLOADAVATARURLOOK = "Upload avatar url OK";
    private final static String UPLOADAVATARURLFAILED = "Upload avatar url failed";
    private final static Integer OKSTATUS = 0;
    private final static Integer FAILEDSTATUS = 1;

    @Override
    public @NotNull BaseResponse webGetUserInfo(@NotNull WebGetUserInfoModel webGetUserInfoModel){
        BaseResponse baseResponse = new BaseResponse();
        Useraccount useraccount = useraccountMapper.selectByPrimaryKey(webGetUserInfoModel.getUserName());
        if (useraccount != null){
            Map<String,Object> userInfoMap = new HashMap<String, Object>();
            userInfoMap.put("userName",useraccount.getUsername());
            userInfoMap.put("nickName",useraccount.getNickname());
            userInfoMap.put("sign",useraccount.getSign());
            userInfoMap.put("avatarUrl",useraccount.getAvatarurl());
            baseResponse.setData(userInfoMap);
            baseResponse.setMessage(GETUSERINFOOK);
            baseResponse.setStatus(OKSTATUS);
        } else {
            baseResponse.setMessage(GETUSERINFOFAILED);
            baseResponse.setStatus(FAILEDSTATUS);
        }


        return baseResponse;
    }

    /**
     * 实现头像路径的上传与存储
     * @param uploadAvatarUrlModel
     * @return
     */
    @Override
    public @NotNull BaseResponse uploadAvatarUrl(@NotNull UploadAvatarUrlModel uploadAvatarUrlModel){
        BaseResponse baseResponse = new BaseResponse();
        Useraccount useraccount = useraccountMapper.selectByPrimaryKey(uploadAvatarUrlModel.getUserName());
        if (useraccount != null){
            useraccount.setAvatarurl(uploadAvatarUrlModel.getAvatarUrl());
            useraccountMapper.updateByPrimaryKey(useraccount);

            baseResponse.setStatus(OKSTATUS);
            baseResponse.setMessage(UPLOADAVATARURLOOK);
        } else {
            baseResponse.setStatus(FAILEDSTATUS);
            baseResponse.setMessage(UPLOADAVATARURLFAILED);
        }
        return baseResponse;
    }

    /**
     * 获取其他用户的个人信息
     * @param webSelfOtherUserNameModel
     * @return
     */
    @Override
    public @NotNull BaseResponse webGetOtherUserInfo(@NotNull WebSelfOtherUserNameModel webSelfOtherUserNameModel){

        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getSelfUserName()) != null){
            if (useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getOtherUserName()) != null){
                Useraccount useraccount = useraccountMapper.selectByPrimaryKey(webSelfOtherUserNameModel.getOtherUserName());
                useraccount.setPassword("");
                useraccount.setIdnumber("");
                baseResponse.setMessage(GETUSERINFOOK);
                baseResponse.setStatus(OKSTATUS);
                baseResponse.setData(useraccount);
            }else {
                baseResponse.setMessage(GETUSERINFOFAILED);
                baseResponse.setStatus(FAILEDSTATUS);
            }
        }else {
            baseResponse.setMessage(GETUSERINFOFAILED);
            baseResponse.setStatus(FAILEDSTATUS);
        }
        return baseResponse;
    }
}
