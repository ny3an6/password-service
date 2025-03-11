package com.ndmitrenko.passwordservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    VALIDATION("validation"),
    RUNTIME("runtime");

    private final String name;
}
