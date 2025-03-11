package com.ndmitrenko.passwordservice.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.ndmitrenko.passwordservice..*(..))")
    public void allMethodsInApp() {

    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllers() {

    }

    @Before("controllers()")
    public void logControllers(JoinPoint joinPoint) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.debug(">> Вызывается endpoint {}, с параметрами {}", request.getRequestURI(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "allMethodsInApp() && !controllers()", returning = "result")
    public void logAllMethodsInApp(JoinPoint joinPoint, Object result) {
        log.trace(">> Был вызван метод {}, класса {}, c параметрами {}, получен результат {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getName(),
                Arrays.toString(joinPoint.getArgs()), result
        );
    }

}
