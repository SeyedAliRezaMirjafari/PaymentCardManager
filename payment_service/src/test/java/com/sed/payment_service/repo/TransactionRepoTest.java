package com.sed.payment_service.repo;

import com.google.gson.Gson;
import com.sed.payment_service.dataaccess.models.Transaction;
import com.sed.payment_service.dataaccess.repositories.TransactionRepo;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionRepoTest {
    @Autowired
    TransactionRepo transactionRepo;

    @Test
    @DisplayName("save card success")
    @Order(1)
    public void saveTest() {
        Transaction transaction = save("1234-1234-1234-1234", "-1234-1234-1234-1234", "123456", "125", "9910", 100l, 1l, LocalDate.now());
        Assertions.assertNotNull(transaction.getId());
        Assertions.assertNotNull(transaction.getTransactionId());
    }

    @Test
    @DisplayName("get report")
    @Order(2)
    public void getReport() {
        save("1234-1234-1234-1234", "-1234-1234-1234-1234", "123456", "125", "9910", 100l, 1l, LocalDate.now());
        save("1234-1234-1234-1234", "-1234-1234-1234-1234", "123456", "125", "9910", 200l, 1l, LocalDate.now().minusDays(2));
        save("1234-1234-1234-1234", "-1234-1234-1234-1234", "123456", "125", "9910", 300l, 1l, LocalDate.now().minusDays(1));
        List<Transaction> transactions=transactionRepo.findTransactionByUserIdAndTransactionDateBetween(1l, LocalDate.now().minusDays(4), LocalDate.now().plusDays(1));
        Assertions.assertEquals(transactions.size(),3);
    }


    public Transaction save(String from, String to, String pin, String cvv2, String expireDate, Long amount, Long userId, LocalDate localDate) {
        Transaction transaction = new Transaction();
        transaction.setFromCardNumber(from);
        transaction.setToCardNumber(to);
        transaction.setAmount(amount);
        transaction.setPin2(pin);
        transaction.setcvv2(cvv2);
        transaction.setUserId(userId);
        transaction.setExpireDate(expireDate);
        transaction.setTransactionDate(localDate);
        try {
            transactionRepo.save(transaction);
        } catch (Exception e) {

        }
        return transaction;
    }
}
