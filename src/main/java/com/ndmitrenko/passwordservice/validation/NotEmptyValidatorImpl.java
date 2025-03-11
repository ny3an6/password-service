package com.ndmitrenko.passwordservice.validation;

import com.ndmitrenko.passwordservice.exception.AppException;
import com.ndmitrenko.passwordservice.exception.ErrorType;
import org.springframework.http.HttpStatus;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.Collection;

public class NotEmptyValidatorImpl implements FieldValidator {

    private static final String EMPTY_STRING = "Field must not be empty. Class: ";

    @Override
    public void validate(Object entity, Field field) {
        try {
            if (Collection.class.isAssignableFrom(field.getType())) {
                Collection<?> fieldValue = (Collection<?>) field.get(entity);
                if (fieldValue == null || fieldValue.isEmpty()) {
                    throw new AppException(
                            EMPTY_STRING + entity.getClass().getName() + ", field: " + field.getName(),
                            ErrorType.VALIDATION.getName(),
                            HttpStatus.BAD_REQUEST
                    );
                }
            } else if (String.class.isAssignableFrom(field.getType())) {
                String fieldValue = (String) field.get(entity);
                if (fieldValue == null || fieldValue.isEmpty()) {
                    throw new AppException(
                            EMPTY_STRING + entity.getClass().getName() + ", field: " + field.getName(),
                            ErrorType.VALIDATION.getName(),
                            HttpStatus.BAD_REQUEST
                    );
                }
            } else {
                if (field.get(entity) == null) {
                    throw new AppException("Field " + field.getName() + " is required", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (IllegalAccessException e) {
            throw new ValidationException(e);
        }
    }
}
