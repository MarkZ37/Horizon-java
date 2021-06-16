package com.markz.horizon.config.aop;

import com.alibaba.fastjson.JSON;
import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.utils.MyThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
@Slf4j
public class TokenRefreshConfig {

    @Pointcut("execution(public * com.markz.horizon.controller.api.*.*(..))")
    public void webLog(){

    }
    @Before("webLog()")
    public void dealBefore(){

    }

    @AfterReturning(pointcut = "webLog()",returning = "ret")
    public BaseResponse dealAfterReturning (Object ret){


        BaseResponse result = new BaseResponse() ;
        if (ret instanceof BaseResponse){
            result = (BaseResponse) ret;
        }
        Map<String,Object> newToken = MyThreadLocal.getData();
        String tokenString = String.valueOf(newToken.get("refreshToken"));
        if (tokenString != null){
            result.setRefreshToken(tokenString);
        }
        return result;
    }

}
