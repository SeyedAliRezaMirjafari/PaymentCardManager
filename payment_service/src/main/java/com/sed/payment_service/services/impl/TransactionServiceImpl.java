package com.sed.payment_service.services.impl;

import com.google.gson.Gson;
import com.sed.payment_service.common.enums.TransactionState;
import com.sed.payment_service.dataaccess.DTO.TransactionDTO;
import com.sed.payment_service.dataaccess.models.Transaction;
import com.sed.payment_service.dataaccess.repositories.TransactionRepo;
import com.sed.payment_service.services.TransactionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    protected static final org.slf4j.Logger nofitLog = LoggerFactory.getLogger("RabbitMQ");
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    TransactionRepo transactionRepo;
    FactoryBankService factoryBankService;
    Gson gson;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo, FactoryBankService factoryBankService,Gson gson) {
        this.transactionRepo = transactionRepo;
        this.factoryBankService = factoryBankService;
        this.gson=gson;
    }

    @Override
    public TransactionDTO transferCard(TransactionDTO transactionDTO) {
        Transaction transaction = transactionDTO.toTransation();
        transaction.setState(TransactionState.BEGIN.getCode());
        transactionRepo.save(transaction);
        if (factoryBankService.transferCard(transaction.getFromCardNumber(), transaction.getToCardNumber(), transaction.getcvv2(),
                transactionDTO.getPin2(), transaction.getExpireDate(), transaction.getAmount()).getStatusCode().equals(HttpStatus.OK)) {
            transaction.setStatus(true);
            transaction.setTransactionDate(LocalDate.now());
            transaction.setTransactionTime(LocalTime.now());
            transaction.setState(TransactionState.COMMIT.getCode());
            transactionRepo.save(transaction);
        } else {
            transaction.setStatus(false);
            transaction.setState(TransactionState.FAILED.getCode());
            transaction.setTransactionDate(LocalDate.now());
            transaction.setTransactionTime(LocalTime.now());
            logger.error("transfer failed");
            transactionRepo.save(transaction);
        }
        TransactionDTO result=TransactionDTO.fromTransaction(transaction);
        nofitLog.info(gson.toJson(result));
        return result;
    }

    @Override
    public String getTransactionReport(Long userId, String start, String end) {
        LocalDate startDate = fromString(start);
        LocalDate endDate = fromString(end);

        if (endDate == null) {
            endDate = LocalDate.now().minusDays(1);
        }
        if (startDate == null) {
            startDate = LocalDate.now().minusDays(10);
        }
        List<Transaction> transactions = transactionRepo.findTransactionByUserIdAndTransactionDateBetween(userId, startDate, endDate);
        /*Map<String, Map<Integer, List<Transaction>>> map = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getFromCardNumber,
                        Collectors.groupingBy(Transaction::getState)));*/
        Map<String, Map<Boolean, Long>> map = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getFromCardNumber,
                        Collectors.groupingBy(Transaction::getStatus, Collectors.counting())));
        return new Gson().toJson(map);
    }

    private LocalDate fromString(String s) {
        if (s == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(s, formatter);
    }
}
