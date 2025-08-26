package com.example.homework_spring_course.anotation;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("@annotation(LoggerTime)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String body = null;
        if (request instanceof ContentCachingRequestWrapper wrapper) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                body = new String(buf, StandardCharsets.UTF_8);
            }
        }
        log.info("\n========================> \n Request to controller start - {} \n ==========================>", body);
        try {
            return joinPoint.proceed();
        } finally {
            long spent = (System.currentTimeMillis() - start) / 1000;
            log.info(" Request spent : {}s", spent);
        }
    }
}
