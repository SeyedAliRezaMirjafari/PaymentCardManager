package com.sed.payment_service.services.impl;

import com.sed.payment_service.services.BankService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service("p2")
public class Provider2BankServiceImpl implements BankService {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(Provider2BankServiceImpl.class);
    @Value("${bank.provider2.host}")
    private String host;
    @Value("${bank.provider2.path}")
    private String path;

    @Override
    public ResponseEntity transferCard(String from, String to, String cvv2, String pi2, String expireDate, Long amount) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> map = new HashMap();
        map.put("source", from);
        map.put("target", to);
        map.put("cvv2", cvv2);
        map.put("expire", expireDate);
        map.put("pin2", pi2);
        map.put("amount", amount);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(map, headers);
        try {
            return restTemplate.postForEntity(host+path, request, String.class);
        } catch (Exception e) {
            logger.error("provider1 payment failed");
            return new ResponseEntity("", HttpStatus.EXPECTATION_FAILED);
        }

    }
}
