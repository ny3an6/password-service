package com.ndmitrenko.passwordservice.aspect;

import com.ndmitrenko.passwordservice.validation.ParamValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAspect {

    private final ParamValidator validator;

    @Pointcut("@annotation(com.ndmitrenko.passwordservice.annotation.validation.ValidateParamsNotEmpty)")
    public void validateParams() {

    }

    @Before("validateParams()")
    public void validateNotEmpty(JoinPoint joinPoint) {
        Stream.of(joinPoint.getArgs()).forEach(validator::validate);
    }
}
