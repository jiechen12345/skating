package com.oppo.business;


import com.oppo.annotation.Action;
import com.oppo.api.AccommodateAjaxApi;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by JieChen on 2018/5/28.
 */
//宣告他是AOP
@Order(1)//代表優先級最高
@Aspect
@Component
public class LogAspect {
    Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
    // pointCut
    @Pointcut("execution(@com.oppo.annotation.Action * *(..))")
    //@Pointcut("@annotation(com.example.demo.annotation.Action)")
    public void log() {
        //LOGGER.info("---beging....------------------------------.");
    }

    //前通知
    @Before("log()")
    public void beforeMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        LOGGER.info("action: " + action.value() + "  Args: " + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 后置通知
     */
    @AfterReturning(pointcut = "log()", returning = "retValue")
    public void doAfterController(JoinPoint joinPoint, Object retValue) {
        LOGGER.info("return: " + retValue);
    }

    /**
     * 異常通知
     */
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void afterThrowning(JoinPoint joinPoint, Exception e) {
        String method = joinPoint.getSignature().getName();
        LOGGER.error("action: " + method + "   exception: " + e.toString());
    }


    /**
     * 環繞通知
     */
    /*
    @Around("execution(* com.example.demo.api.*.*(..))")
    public void aroundApi(ProceedingJoinPoint pjp) throws Throwable {
        //=前置通知
        System.out.println("action名称 " + pjp.getSignature().getName() + "  -參數" + Arrays.asList(pjp.getArgs()));
        //執行目標方法
        Object res = pjp.proceed();
        //=後置通知
        System.out.println("action名称 " + pjp.getSignature().getName() + "  -return is" + res);

    }
*/
}
