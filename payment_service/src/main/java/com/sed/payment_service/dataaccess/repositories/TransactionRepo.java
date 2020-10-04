package com.sed.payment_service.dataaccess.repositories;

import com.sed.payment_service.dataaccess.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    public List<Transaction> findTransactionByUserIdAndTransactionDateBetween(Long userId, LocalDate start,LocalDate end);
}
