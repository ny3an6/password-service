package com.ndmitrenko.passwordservice.validation;

import java.lang.reflect.Field;

public interface FieldValidator {
    void validate(Object entity, Field field);
}
