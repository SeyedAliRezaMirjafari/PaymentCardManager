package com.sed.payment_service.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BankService {
    public ResponseEntity transferCard(String from, String to, String cvv2, String pi2, String expireDate, Long amount);
}
