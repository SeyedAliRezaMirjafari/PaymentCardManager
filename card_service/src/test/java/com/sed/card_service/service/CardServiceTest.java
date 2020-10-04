package com.sed.card_service.service;

import com.sed.card_service.dataaccess.DTO.CardDTO;
import com.sed.card_service.dataaccess.models.Card;
import com.sed.card_service.dataaccess.repositories.CardRepo;
import com.sed.card_service.services.CardService;
import com.sed.card_service.services.impl.CardServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardServiceTest {
    @TestConfiguration
    static class CardServiceConfig {

        @Bean
        public CardService cardService(CardRepo cardRepo) {
            return new CardServiceImpl(cardRepo);
        }
    }
    @Autowired
     CardService cardService;
    @MockBean
    CardRepo cardRepo;




    @Test
    @DisplayName("get all user card")
    @Order(1)
    public void getAllUserCard() {
        List<Card> cards= Arrays.asList(new Card[]{
                new Card(1l,"mycard", "1234-1234-1234-1234", "meli", 1l,null,null,null),
                new Card(1l,"mycard1", "1234-1234-1234-1235", "meli", 1l,null,null,null)

        });
        Mockito.when(cardRepo.findCardByUserIdAndDeleted(1l,false))
                .thenReturn(cards);
        List<CardDTO> cardDTOS=cardService.getUserCard(1l);
        Assertions.assertEquals(cardDTOS.size(),2);
    }

    @Test
    @DisplayName("remove card")
    @Order(2)
    public void removiCard() {

        Card card=new Card(1l,"mycard", "1234-1234-1234-1234", "meli", 1l,null,null,null);
        card.setDeleted(false);

        Mockito.when(cardRepo.findCardByIdAndUserId(1l,1l))
                .thenReturn(card);
        Mockito.when(cardRepo.save(card))
                .thenReturn(card);
        CardDTO cardDTO=new CardDTO();
        cardDTO.setUserId(1l);
        cardDTO.setId(1l);
        CardDTO result=cardService.removeCard(cardDTO);
        Assertions.assertEquals(result.getId(),cardDTO.getId());
    }
    @Test
    @DisplayName("register card success")
    @Order(3)
    public void registerCard() {

        Mockito.when(cardRepo.findCardByCardNumberAndUserId("1234-1234-1234-1234",1l))
                .thenReturn(null);

        CardDTO cardDTO=new CardDTO();
        cardDTO.setCardName("myCard");
        cardDTO.setBank("meli");
        cardDTO.setCardNumber("1234-1234-1234-1234");
        cardDTO.setUserId(1l);
        Mockito.when(cardRepo.save(cardDTO.toCard()))
                .thenReturn(new Card(1l,"myCard", "1234-1234-1234-1234", "meli", 1l,null,null,null));
    }
}
