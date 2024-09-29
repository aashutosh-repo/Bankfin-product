package com.fin.bancs.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class RestControllerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());
        return true; // Proceed to the next interceptor or controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Request completed for URI: {}", request.getRequestURI());
    }
}

