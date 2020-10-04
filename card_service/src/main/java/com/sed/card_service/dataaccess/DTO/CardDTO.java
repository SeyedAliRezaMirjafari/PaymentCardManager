package com.sed.card_service.dataaccess.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sed.card_service.dataaccess.models.Card;

import java.security.PublicKey;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    private Long id;
    private String cardName;
    private String cardNumber;
    private String bank;
    private Long userId;
    private Boolean expired;
    private String expire_Date;
    private String cvv2;

    public CardDTO() {
    }

    public CardDTO(Long id, String cardName, String cardNumber, String bank, Long userId, Boolean expired, String expire_Date, String cvv2) {
        this.id = id;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.userId = userId;
        this.expired = expired;
        this.expire_Date = expire_Date;
        this.cvv2 = cvv2;
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

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
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

    public Card toCard() {
        return new Card(this.id, this.cardName, this.cardNumber, this.bank, this.userId, this.expired, this.expire_Date, this.cvv2);
    }
    public static CardDTO fromCard(Card card) {
        return new CardDTO(card.getId(), card.getCardName(), card.getCardNumber(), card.getBank(), card.getUserId(), card.getExpired(), card.getExpire_Date(), card.getcvv2());
    }

}
