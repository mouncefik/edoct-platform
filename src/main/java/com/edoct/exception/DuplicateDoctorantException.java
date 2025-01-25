package com.edoct.exception;

import lombok.Getter;

@Getter
public class DuplicateDoctorantException extends RuntimeException {
    public DuplicateDoctorantException(String message) {
        super(message);
    }
}