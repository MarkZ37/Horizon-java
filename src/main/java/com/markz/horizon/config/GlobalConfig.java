package com.markz.horizon.config;

import com.markz.horizon.utils.JwtInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class GlobalConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new JwtInterceptor()).excludePathPatterns("/api/user/login","/api/user/regist");//放掉某些特定不需要校验token的路由
    }
}
