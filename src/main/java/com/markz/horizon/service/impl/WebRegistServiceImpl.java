package com.markz.horizon.service.impl;

import com.markz.horizon.entity.base.BaseResponse;

import com.markz.horizon.entity.model.WebRegistModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.service.WebRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class WebRegistServiceImpl implements WebRegistService {

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String REGISTOK = "Login OK";
    private final static String REGISTFAILED = "Login failed";
    private final static String ACCOUNTEXIST = "Username exists";
    private final static Integer REGISTOKSTATUS = 0;
    private final static Integer REGISTFAILEDSTATUS = 1;
    @Override
    public @NotNull BaseResponse webRegistService(@NotNull WebRegistModel webRegistModel){
        System.out.println(webRegistModel);
        BaseResponse baseResponse = new BaseResponse();
        if (useraccountMapper.selectByPrimaryKey(webRegistModel.getAccount()) != null){
            //账号存在

            System.out.println("账号存在");
            baseResponse.setMessage(ACCOUNTEXIST);
            baseResponse.setStatus(REGISTFAILEDSTATUS);
        }else{
            //账号不存在
            System.out.println("账号不存在");
            baseResponse.setMessage(REGISTOK);
            baseResponse.setStatus(REGISTOKSTATUS);
        }
        return baseResponse;
    }
}
