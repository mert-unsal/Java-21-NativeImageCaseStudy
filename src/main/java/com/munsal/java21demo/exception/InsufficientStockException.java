package com.munsal.java21demo.exception;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends BaseException {
    public InsufficientStockException(String code) {
        super(HttpStatus.BAD_REQUEST, code);
    }
}
