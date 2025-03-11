package com.ndmitrenko.passwordservice.configuration;

import com.ndmitrenko.passwordservice.annotation.validation.NotEmpty;
import com.ndmitrenko.passwordservice.validation.AnnotationBasedParamValidatorImpl;
import com.ndmitrenko.passwordservice.validation.FieldValidator;
import com.ndmitrenko.passwordservice.validation.NotEmptyValidatorImpl;
import com.ndmitrenko.passwordservice.validation.ParamValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
@Slf4j
public class Configuration {

    @Bean
    @Scope("prototype")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ParamValidator getParamValidator() {
        Map<Class<? extends Annotation>, FieldValidator> validatorMap = new HashMap<>();
        validatorMap.put(NotEmpty.class, new NotEmptyValidatorImpl());
        return new AnnotationBasedParamValidatorImpl(validatorMap);
    }

}
