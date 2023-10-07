package com.munsal.java21demo.exception;

import org.springframework.http.HttpStatus;

public class DomainNotFoundException extends BaseException {
    public <T> DomainNotFoundException(String code, T id) {
        super(HttpStatus.BAD_REQUEST, code, new Object[]{id});
    }
}
