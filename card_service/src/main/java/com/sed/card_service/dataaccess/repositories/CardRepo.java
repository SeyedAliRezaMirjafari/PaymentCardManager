package com.sed.card_service.dataaccess.repositories;

import com.sed.card_service.dataaccess.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepo extends JpaRepository<Card,Long> {
    public List<Card> findCardByUserIdAndDeleted(Long userId,Boolean deleted);
    public Card findCardByCardNumberAndUserId(String cardNumber,Long userId);
    public Card findCardByIdAndUserId(Long id,Long userId);
}
