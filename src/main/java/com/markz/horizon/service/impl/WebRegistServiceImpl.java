package com.markz.horizon.service.impl;

import com.markz.horizon.entity.Useraccount;
import com.markz.horizon.entity.base.BaseResponse;

import com.markz.horizon.entity.model.WebRegistModel;
import com.markz.horizon.mapper.UseraccountMapper;
import com.markz.horizon.service.WebRegistService;
import com.markz.horizon.utils.IdCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;


@Service
@Transactional
public class WebRegistServiceImpl implements WebRegistService {

    @Autowired
    UseraccountMapper useraccountMapper;

    private final static String REGISTOK = "Regist OK";
    private final static String REGISTFAILED = "Regist failed";
    private final static String ACCOUNTEXIST = "Username exists";
    private final static Integer REGISTOKSTATUS = 0;
    private final static Integer REGISTFAILEDSTATUS = 1;
    @Override
    public @NotNull BaseResponse webRegistService(@NotNull WebRegistModel webRegistModel){
        System.out.println(webRegistModel);
        BaseResponse baseResponse = new BaseResponse();
        IdCardUtil idCardUtil = new IdCardUtil();
        String verification= idCardUtil.IdentityCardVerification(webRegistModel.getIdNumber());
        if (verification.equals("correct")){
            //身份证号验证通过
            if (useraccountMapper.selectByPrimaryKey(webRegistModel.getAccount()) != null){
                //用户存在
                baseResponse.setStatus(REGISTFAILEDSTATUS);
                baseResponse.setMessage(ACCOUNTEXIST);
            }else {
                //用户不存在
                Useraccount useraccount = new Useraccount();
                useraccount.setUsername(webRegistModel.getAccount());
                useraccount.setPassword(webRegistModel.getPassword());
                useraccount.setNickname(webRegistModel.getNickname());
                useraccount.setIdnumber(webRegistModel.getIdNumber());
                useraccountMapper.insert(useraccount);
                baseResponse.setStatus(REGISTOKSTATUS);
                baseResponse.setMessage(REGISTOK);

            }
        }else {
            //身份证验证不通过
            baseResponse.setStatus(REGISTFAILEDSTATUS);
            baseResponse.setMessage(verification);
        }
        return baseResponse;
    }
}
