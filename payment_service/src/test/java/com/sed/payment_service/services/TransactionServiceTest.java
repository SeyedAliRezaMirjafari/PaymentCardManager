package com.sed.payment_service.services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sed.payment_service.dataaccess.DTO.TransactionDTO;
import com.sed.payment_service.dataaccess.models.Transaction;
import com.sed.payment_service.dataaccess.repositories.TransactionRepo;
import com.sed.payment_service.services.impl.FactoryBankService;
import com.sed.payment_service.services.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionServiceTest {
    @TestConfiguration
    static class TrasactionServiceConfig {

        @Bean
        public TransactionService transactionService(TransactionRepo transactionRepo, FactoryBankService factoryBankService, Gson gson) {
            return new TransactionServiceImpl(transactionRepo, factoryBankService, gson);
        }

        @Bean
        public Gson gson() {
            return new Gson();
        }
    }
    @MockBean
    FactoryBankService factoryBankService;

    @Autowired
    TransactionService transactionService;
    @Autowired
    Gson gson;

    @MockBean
    TransactionRepo transactionRepo;

    @Test
    @DisplayName("get report")
    @Order(1)
    public void getReport() {
        Transaction transaction = new Transaction();
        transaction.setFromCardNumber("1234-1234-1234-1234");
        transaction.setStatus(true);
        Transaction transaction1 = new Transaction();
        transaction1.setFromCardNumber("1234-1234-1234-1234");
        transaction1.setStatus(false);
        Transaction transaction2 = new Transaction();
        transaction2.setFromCardNumber("1234-1234-1234-1234");
        transaction2.setStatus(true);
        List<Transaction> ts = Arrays.asList(new Transaction[]{transaction, transaction1,transaction2});
        Mockito.when(transactionRepo.findTransactionByUserIdAndTransactionDateBetween(1l, LocalDate.now().minusDays(1), LocalDate.now()))
                .thenReturn(ts);
        String result = transactionService.getTransactionReport(1l, "2020/10/03", "2020/10/04");
        Type type = new TypeToken<Map<String, Map<Boolean, Long>>>() {}.getType();
        Map<String, Map<Boolean, Long>> map=gson.fromJson(result,type);
        Assertions.assertEquals(map.get("1234-1234-1234-1234").get(true),2);
        Assertions.assertEquals(map.get("1234-1234-1234-1234").get(false),1);

    }
    @Test
    @DisplayName("register transaction success")
    @Order(3)
    public void registerTransaction() {

        Mockito.when(factoryBankService.transferCard("1234-1234-1234-1234", "1234-1234-1234-1235", "123",
                "12345678", "9910", 100l))
                .thenReturn(new ResponseEntity(HttpStatus.OK));

        TransactionDTO transaction=new TransactionDTO();
        transaction.setFromCardNumber("1234-1234-1234-1234");
        transaction.setToCardNumber("1234-1234-1234-1235");
        transaction.setAmount(100l);
        transaction.setPin2("12345678");
        transaction.setcvv2("123");
        transaction.setUserId(1l);
        transaction.setExpireDate("9910");
        TransactionDTO result=transactionService.transferCard(transaction);
        Assertions.assertEquals(result.getStatus(),true);
    }

}
