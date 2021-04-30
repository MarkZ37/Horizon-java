package com.markz.horizon.utils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jwt拦截器
 * 验证token
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{

        String authHeader=request.getHeader("Authorization");
        if(authHeader==null||!authHeader.startsWith("Bearer:")){
            return false;
        }
        //获得token
        String token = authHeader.substring(7);
        //验证token

        return JwtUtil.verfy(token);
    }

}
