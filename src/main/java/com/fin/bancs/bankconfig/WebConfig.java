package com.fin.bancs.bankconfig;

import com.fin.bancs.aop.RestControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //Interceptors manage HTTP-level concerns, like logging requests and responses.
    @Autowired
    private RestControllerInterceptor restControllerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restControllerInterceptor).addPathPatterns("/**");
    }
}
