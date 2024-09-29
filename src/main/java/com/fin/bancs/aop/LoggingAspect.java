package com.fin.bancs.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    //Aspects manage service-level concerns, like logging method entries and exits.
    @Around("execution(* com.fin.bancs..*(..))")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis()- start;
        log.info("The Method : {} Executed in : {}ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }

    @Before("execution(* com.fin.bancs..*(..))")
    public void beforeMethodCall(JoinPoint joinPoint){
        log.info("Calling Method - {}", joinPoint.getSignature().getName());
    }
    @After("execution(* com.fin.bancs..*(..))")
    public void logAfterCall(JoinPoint joinPoint){
        log.info("Exiting Method - {}",joinPoint.getSignature().getName());
    }

}
