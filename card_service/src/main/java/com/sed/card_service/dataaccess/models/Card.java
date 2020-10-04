package com.sed.card_service.dataaccess.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "card")
public class Card {
    @Id
    /*@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )*/
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_name")
    @Expose
    private String cardName;
    @Column(name = "card_number",length = 19,nullable = false)
    @Expose
    private String cardNumber;
    @Column
    @Expose
    private String bank;
    @Column(name = "user_id",nullable = false)
    @Expose
    private Long userId;
    @Column
    private Boolean deleted=false;
    @Column
    @Expose
    private Boolean expired;

    @Column(name = "expire_date",length = 5)
    private String expire_Date;
    @Column(length = 4)
    private String cvv2;


    @Column(name = "insert_date")
    private Date insertDate;
    @Column(name = "update_date")
    private Date updateDate;

    public Card() {
    }

    public Card(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getExpire_Date() {
        return expire_Date;
    }

    public void setExpire_Date(String expire_Date) {
        this.expire_Date = expire_Date;
    }

    public String getcvv2() {
        return cvv2;
    }

    public void setcvv2(String cvv2) {
        this.cvv2 = cvv2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Card(Long id, String cardName, String cardNumber, String bank, Long userId, Boolean expired, String expire_Date, String cvv2) {
        this.id = id;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.userId = userId;
        this.expired = expired;
        this.expire_Date = expire_Date;
        this.cvv2 = cvv2;
    }

    @PrePersist
    public void prePersist(){
        this.insertDate=new Date();
    }
    @PreUpdate
    public void preUpdate(){
        this.updateDate=new Date();
    }
}
