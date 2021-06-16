package com.markz.horizon.config;

import com.markz.horizon.utils.JwtUtil;
import com.markz.horizon.utils.MyThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt拦截器
 * 验证token
 */
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {

//    private static final Integer TOKEN_NORMAL_CODE = 444;
//    private static final Integer TOKEN_REFRESH_CODE = 555;
//    private static final Integer TOKEN_INVALID_CODE = 666;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Map<String,Object> responseData = new HashMap<String, Object>();

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        String token =request.getHeader("Authorization");

        if(StringUtils.isEmpty(token)){
            log.info("拦截用户非法请求路劲：{}",request.getRequestURI());
            return false;
        }
        //获得token
        //验证token

        log.info("进入JWT");
        if(!JwtUtil.verify(token)){
            //token失效
            log.error("用户token失效{0}",request.getRequestURI());

            return false;
        } else {
            //验证是否濒死
            if (! JwtUtil.checkNearDeathToken(token)){
                //濒死
                Map<String,Object> refreshTokenMap = new HashMap<String, Object>();
                refreshTokenMap.put("refreshToken",JwtUtil.refreshToken(token));
                MyThreadLocal.setData(refreshTokenMap);

            }
        }
        log.info("用户请求成功{}",request.getRequestURI());

        return true;
    }


}
