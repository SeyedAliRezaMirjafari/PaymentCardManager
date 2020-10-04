package com.sed.payment_service.dataaccess.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_id",nullable = false,unique = true)
    private String transactionId;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @Column(name = "from_card_number",nullable = false)
    private String fromCardNumber;
    @Column(name = "to_card_number",nullable = false)
    private String toCardNumber;
    private String cvv2;
    @Column(name = "expire_date")
    private String expireDate;
    private Long amount;
    private String pin2;
    private Boolean status;
    private int state;
    @Column(name = "transation_date", columnDefinition = "DATE")
    private LocalDate transactionDate;
    @Column(name = "transaction_time", columnDefinition = "TIME")
    private LocalTime transactionTime;

    @Column(name = "insert_date")
    private Date insertDate;
    @Column(name = "update_date")
    private Date updateDate;

    public Transaction() {
    }

    public Transaction(Long id) {
        this.id = id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public Date getInsertDate() {
        return insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Transaction(Long id, String transactionId, Long userId, String fromCardNumber, String toCardNumber, String cvv2, String expireDate, Long amount, Boolean status, LocalDate transactionDate, LocalTime transactionTime) {
        this.id = id;
        this.transactionId = transactionId;
        this.userId = userId;
        this.fromCardNumber = fromCardNumber;
        this.toCardNumber = toCardNumber;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
        this.amount = amount;
        this.status = status;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
    }

    @PrePersist
    public void prePersist() {
        this.insertDate = new Date();
        this.transactionId= UUID.randomUUID().toString().replace("-","");
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = new Date();
    }
}
