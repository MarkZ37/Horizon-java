package com.markz.horizon.service.impl;

import com.markz.horizon.entity.Useraccount;
import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WebGetUserInfoModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class WebUserServiceImpl implements WebUserService {

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String GETUSERINFOOK = "Get userinfo OK";
    private final static String GETUSERINFOFAILED = "Get userinfo failed";
    private final static Integer GETUSERINFOOKSTATUS = 0;
    private final static Integer GETUSERINFOFAILEDSTATUS = 1;

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
            baseResponse.setStatus(GETUSERINFOOKSTATUS);
        } else {
            baseResponse.setMessage(GETUSERINFOFAILED);
            baseResponse.setStatus(GETUSERINFOFAILEDSTATUS);
        }

        return baseResponse;
    }
}
