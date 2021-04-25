package com.markz.horizon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**").allowedOrigins("http://localhost:8081")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(30*1000);
    }
}
