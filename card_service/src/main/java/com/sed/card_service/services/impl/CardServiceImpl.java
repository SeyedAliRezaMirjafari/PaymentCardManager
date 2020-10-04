package com.sed.card_service.services.impl;

import com.sed.card_service.apiControllers.exceptionHandler.CardExceptionHandler;
import com.sed.card_service.common.exceptions.ExistsRecordException;
import com.sed.card_service.common.exceptions.NotFoundException;
import com.sed.card_service.dataaccess.DTO.CardDTO;
import com.sed.card_service.dataaccess.models.Card;
import com.sed.card_service.dataaccess.repositories.CardRepo;
import com.sed.card_service.services.CardService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);
    CardRepo cardRepo;

    @Autowired
    public CardServiceImpl(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Override
    public CardDTO registerCard(CardDTO cardDTO) {
        /*check card is valid*/
        Card card=cardRepo.findCardByCardNumberAndUserId(cardDTO.getCardNumber(),cardDTO.getUserId());
        if(card!=null){
            if (card.getDeleted().equals(true)){
                card.setDeleted(false);
                cardRepo.save(card);
                logger.warn("card number: "+cardDTO.getCardNumber()+" is deleted");
                return CardDTO.fromCard(card);
            }else {
                logger.error("card number: "+cardDTO.getCardNumber());
                throw new ExistsRecordException("card number: "+cardDTO.getCardNumber()+" exists");
            }

        }
        Card resultCard=cardRepo.save(cardDTO.toCard());
        return CardDTO.fromCard(resultCard);
    }

    @Override
    public CardDTO removeCard(CardDTO cardDTO) {
        Card card=cardRepo.findCardByIdAndUserId(cardDTO.getId(),cardDTO.getUserId());
        if (card==null || card.getDeleted().equals(true)){
            logger.error("card id: "+cardDTO.getId()+" not found");
            throw new NotFoundException("card id: "+cardDTO.getId());
        }
        card.setDeleted(true);
        cardRepo.save(card);
        return cardDTO;
    }

    @Override
    public List<CardDTO> getUserCard(Long userId) {

        List<Card> cards=cardRepo.findCardByUserIdAndDeleted(userId,false);
        if (cards==null || cards.size()<0){
            logger.warn("card for user id: "+userId+" not found");
            throw new NotFoundException("user id: "+userId);
        }
        List<CardDTO> result=new ArrayList<>();
        for (Card card : cards) {
            result.add(CardDTO.fromCard(card));
        }
        return result;
    }


}
