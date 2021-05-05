package com.markz.horizon.service.impl;

import com.markz.horizon.entity.Useraccount;
import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.entity.model.WebLoginModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.service.WebLoginService;
import com.markz.horizon.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class WebLoginServiceImpl implements WebLoginService {

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String LOGINOK = "Login OK";
    private final static String PASSWORDWRONG = "Password wrong";
    private final static String ACCOUNTNOTEXIST = "Account not exist";
    private final static Integer LOGINOKSTATUS = 0;
    private final static Integer LOGINFAILEDSTATUS = 1;
    @Override
    public @NotNull BaseResponse webLoginService(@NotNull WebLoginModel webLoginModel){
        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(webLoginModel.getAccount()) != null){
            //账号存在
            if (useraccountMapper.selectByPrimaryKey(webLoginModel.getAccount()).getPassword().equals(webLoginModel.getPassword())){
                //密码正确

                //生成token
                String token = JwtUtil.Sign(webLoginModel.getAccount(),webLoginModel.getPassword());
                Map<String,Object> dataMap = new HashMap<String, Object>();
                Useraccount useraccount = useraccountMapper.selectByPrimaryKey(webLoginModel.getAccount());
                Map<String,Object> userInfoMap = new HashMap<String, Object>();

                userInfoMap.put("userName",useraccount.getUsername());
                userInfoMap.put("avatarUrl",useraccount.getAvatarurl());
                userInfoMap.put("nickName",useraccount.getNickname());
                userInfoMap.put("sign",useraccount.getSign());
                dataMap.put("userInfo",userInfoMap);
                dataMap.put("token",token);


                baseResponse.setMessage(LOGINOK);
                baseResponse.setStatus(LOGINOKSTATUS);
                baseResponse.setData(dataMap);
            }else {
                baseResponse.setStatus(LOGINFAILEDSTATUS);
                baseResponse.setMessage(PASSWORDWRONG);
            }
        } else {
            baseResponse.setMessage(ACCOUNTNOTEXIST);
            baseResponse.setStatus(LOGINFAILEDSTATUS);
        }
        return baseResponse;
    }
}
