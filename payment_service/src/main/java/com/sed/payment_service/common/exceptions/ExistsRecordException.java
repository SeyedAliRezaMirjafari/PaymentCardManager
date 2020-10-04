package com.sed.payment_service.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ExistsRecordException extends RuntimeException {
    public ExistsRecordException(String message) {
        super(message);
    }
}
