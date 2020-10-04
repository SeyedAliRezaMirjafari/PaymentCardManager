package com.sed.payment_service.services;


import com.sed.payment_service.dataaccess.DTO.TransactionDTO;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface TransactionService {
    @Transactional
    TransactionDTO transferCard(TransactionDTO transactionDTO);

    String getTransactionReport(Long userId, String start, String end);
}
