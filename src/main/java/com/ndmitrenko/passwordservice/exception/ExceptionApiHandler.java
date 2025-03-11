package com.ndmitrenko.passwordservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> defaultException(AppException exception) {
        log.error(exception.getMessage(), exception);
        String errorType = StringUtils.hasText(exception.getErrorType()) ? exception.getErrorType() : ErrorType.RUNTIME.getName();
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(Map.of(errorType, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> mismatchException(MethodArgumentTypeMismatchException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(ErrorType.RUNTIME.getName(), exception.getMessage()));
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<Map<String, Object>> psqlException(PSQLException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(ErrorType.RUNTIME.getName(), exception.getMessage()));
    }
}
