package com.sed.notification_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SmsServices {

    public ResponseEntity sendSMS(String number, String msg);
}
