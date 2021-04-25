package com.markz.horizon.service.impl;

import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.WeChatLoginModel;
import com.markz.horizon.entity.model.WebLoginModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.service.WebLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class WebLoginServiceImpl implements WebLoginService {

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String LOGINOK = "Login OK";
    private final static String LOGINFAILED = "Login failed";

    private final static Integer LOGINOKSTATUS = 0;
    private final static Integer LOGINFAILEDSTATUS = 1;
    @Override
    public @NotNull BaseResponse webLoginService(@NotNull WebLoginModel webLoginModel){
        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(webLoginModel.getAccount()) != null){
            baseResponse.setMessage(LOGINOK);
            baseResponse.setStatus(LOGINOKSTATUS);
        }
        return baseResponse;
    }
}
