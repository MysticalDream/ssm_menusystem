package com.mysticaldream.infrastructure.aspectj;

import com.mysticaldream.common.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 *
 * @description: LogAspectJ
 * @date: 2022/6/2 22:37
 * @author: MysticalDream
 */
@Aspect
@Component
public class LogAspectJ {

    private static final Logger log = LoggerFactory.getLogger(LogAspectJ.class);


    @Before(value = "@annotation(controllerLog)")
    public void doBeforeAdvice(JoinPoint joinPoint, Log controllerLog) {
        if (!controllerLog.isSaveRequestParam()) {
            return;
        }
        Signature signature = joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        Object[] args = joinPoint.getArgs();
        log.info("[LogAspectJ]-request url:{}, class: {}, method: {}, param: {}", request != null ? request.getRequestURI() : "null", signature
                .getDeclaringTypeName(), signature.getName(), args[0].toString());
    }

    @AfterReturning(returning = "result", pointcut = "@annotation(controllerLog)")
    public void doAfterReturningAdvice(JoinPoint joinPoint, Log controllerLog, Object result) {
        if (!controllerLog.isSaveResponseData()) {
            return;
        }
        Signature signature = joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        log.info("[LogAspectJ]-response url:{}, class: {}, method: {}, param: {}", request != null ? request.getRequestURI() : "null", signature
                .getDeclaringTypeName(), signature.getName(), result.toString());
    }

    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Log controllerLog, Throwable exception) {
        Signature signature = joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        log.info("[LogAspectJ]-response exception url:{}, class: {}, method: {}", request != null ? request.getRequestURI() : "null", signature
                .getDeclaringTypeName(), signature.getName());
    }


}
