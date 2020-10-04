package com.sed.notification_service.service.impl;

import com.sed.notification_service.service.SmsServices;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsServices {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    @Value("${sms.provider1.host}")
    private String host;
    @Value("${sms.provider1.path}")
    private String path;
    @Override
    public ResponseEntity sendSMS(String number, String msg) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> map= new HashMap<>();

        map.put("msg", msg);
        map.put("target", number);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(map, headers);
        try {
            return restTemplate.postForEntity(host+path, request , String.class );
        }catch (Exception e){
            logger.error("sms provider failed");
            return new ResponseEntity("", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
