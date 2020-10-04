package com.sed.payment_service.common.exceptions;

public class BankServiceException extends RuntimeException {
    public BankServiceException(String message) {
        super(message);
    }
}
