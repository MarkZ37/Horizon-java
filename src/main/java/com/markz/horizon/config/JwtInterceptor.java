package com.markz.horizon.config;

import com.markz.horizon.utils.JwtUtil;
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
        System.out.println(request.getHeader("Authorization"));
        if(StringUtils.isEmpty(token)){
            log.info("拦截用户非法请求路劲：{}",request.getRequestURI());
            return false;
        }
        //获得token
        //验证token
        System.out.println("verify result:" + JwtUtil.verify(token));
        log.info("进入JWT");
        if(!JwtUtil.verify(token)){
            //token失效
            log.error("用户token失效{0}",request.getRequestURI());

            return false;
        }

//        else {
//            //验证是否濒死
//            if (JwtUtil.checkToken(token)){
//                //非濒死
//                responseData.put("tokenCode",TOKEN_NORMAL_CODE);
//            }else {
//                //濒死
//                responseData.put("tokenCode",TOKEN_REFRESH_CODE);
//            }
//        }
        log.info("用户请求成功{}",request.getRequestURI());


//        PrintWriter printWriter = null;
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            printWriter = response.getWriter();
//            printWriter.print(JSON.toJSON(responseData));
////            printWriter.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            printWriter.close();
//        }



        return true;
    }


}
