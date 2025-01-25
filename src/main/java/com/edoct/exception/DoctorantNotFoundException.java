package com.edoct.exception;

import lombok.Getter;

@Getter
public class DoctorantNotFoundException extends RuntimeException {
    public DoctorantNotFoundException(String message) {
        super(message);
    }
}