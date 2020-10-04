package com.sed.payment_service.services.impl;

import com.sed.payment_service.services.BankService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FactoryBankService  {
    private final BeanFactory beanFactory;

    @Autowired
    public FactoryBankService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public ResponseEntity transferCard(String from, String to, String cvv2, String pi2, String expireDate, Long amount) {
        if (from.substring(0,4).endsWith("6037")){
            return beanFactory.getBean("p1", BankService.class).transferCard(from,to,cvv2,pi2,expireDate,amount);
        }else {
            return beanFactory.getBean("p2",BankService.class).transferCard(from,to,cvv2,pi2,expireDate,amount);
        }
    }
}
