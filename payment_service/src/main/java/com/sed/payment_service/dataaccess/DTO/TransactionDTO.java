package com.sed.payment_service.dataaccess.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sed.payment_service.dataaccess.models.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {
    private Long id;
    private String transactionId;
    private Long userId;
    private String fromCardNumber;
    private String toCardNumber;
    private String cvv2;
    private String expireDate;
    private Long amount;
    private String pin2;
    private Boolean status;
    private LocalDate transactionDate;
    private LocalTime transactionTime;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, String transactionId, Long userId, String fromCardNumber, String toCardNumber, String cvv2, String expireDate, Long amount, String pin2, Boolean status, LocalDate transactionDate, LocalTime transactionTime) {
        this.id = id;
        this.transactionId = transactionId;
        this.userId = userId;
        this.fromCardNumber = fromCardNumber;
        this.toCardNumber = toCardNumber;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
        this.amount = amount;
        this.pin2 = pin2;
        this.status = status;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFromCardNumber() {
        return fromCardNumber;
    }

    public void setFromCardNumber(String fromCardNumber) {
        this.fromCardNumber = fromCardNumber;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
    }

    public String getcvv2() {
        return cvv2;
    }

    public void setcvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Transaction toTransation() {
        return new Transaction(this.id, this.transactionId, this.userId, this.fromCardNumber, this.toCardNumber, this.cvv2, this.expireDate,
                this.amount, this.status, this.transactionDate, this.transactionTime);
    }

    public static TransactionDTO fromTransaction(Transaction transaction) {
        return new TransactionDTO(transaction.getId(), transaction.getTransactionId(), transaction.getUserId(), transaction.getFromCardNumber(), transaction.getToCardNumber(),
                null, transaction.getExpireDate(), transaction.getAmount(),
                null, transaction.getStatus(), transaction.getTransactionDate(), transaction.getTransactionTime());
    }
}
