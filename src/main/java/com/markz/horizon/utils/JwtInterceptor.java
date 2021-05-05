package com.markz.horizon.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jwt拦截器
 * 验证token
 */
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        String authHeader=request.getHeader("Authorization");
        System.out.println(request.getHeader("Authorization"));
        if(authHeader==null||!authHeader.startsWith("Bearer:")){

            return false;
        }
        //获得token
        String token = authHeader.substring(7);
        //验证token
        System.out.println("verify result:" + JwtUtil.verify(token));
        log.info("进入JWT");
        return JwtUtil.verify(token);
    }

}
