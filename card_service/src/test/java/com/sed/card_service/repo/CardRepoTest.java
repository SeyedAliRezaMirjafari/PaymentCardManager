package com.sed.card_service.repo;

import com.sed.card_service.dataaccess.models.Card;
import com.sed.card_service.dataaccess.repositories.CardRepo;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardRepoTest {

    @Autowired
    CardRepo cardRepo;

    @Test
    @DisplayName("save card success")
    @Order(1)
    public void saveTest() {
        Card card = save("mycard", "1234-1234-1234-1234", "meli", 1l);
        Assertions.assertNotNull(card.getId());
        Assertions.assertNotNull(card.getDeleted());
        Assertions.assertNotNull(card.getInsertDate());
    }


    @Test
    @DisplayName("get card")
    @Order(2)
    public void getCard() {
        Card c = save("mycard1", "1234-1234-1234-1235", "meli", 1l);
        Optional<Card> card = cardRepo.findById(c.getId());
        Assertions.assertEquals(card.get().getCardNumber(), "1234-1234-1234-1235");
    }

    @Test
    @DisplayName("get all user card")
    @Order(3)
    public void getAllUserCard() {
        save("mycard2", "1234-1234-1234-1236", "meli", 1l);
        save("mycard3", "1234-1234-1234-1237", "meli", 1l);
        save("mycard4", "1234-1234-1234-1238", "meli", 1l);
        save("mycard5", "1234-1234-1234-1239", "meli", 1l);
        List<Card> cards = cardRepo.findCardByUserIdAndDeleted(1l, false);
        Assertions.assertEquals(cards.size(), 4);
    }

    @Test
    @DisplayName("remove card")
    @Order(4)
    public void removiCard() {
        Card c = save("mycard6", "1234-1234-1234-1240", "meli", 1l);
        Optional<Card> cc = cardRepo.findById(c.getId());
        Card card=cc.get();
        Assertions.assertEquals(card.getCardNumber(), "1234-1234-1234-1240");
        Assertions.assertEquals(card.getDeleted(), false);
        card.setDeleted(true);
        cardRepo.save(card);
        Assertions.assertEquals(card.getCardNumber(), "1234-1234-1234-1240");
        Assertions.assertEquals(card.getDeleted(), true);
    }

    @Test
    @DisplayName("save card failed")
    @Order(5)
    public void saveTest1() {
        Card card = null;
        try {
            card= save("mycard", "1234-1234-1234-1234", "meli", null);
        }catch (Exception e){

        }
        Assertions.assertNull(card.getId());

    }



    public Card save(String name, String number, String bank, Long userId) {
        Card card = new Card();
        card.setCardName(name);
        card.setBank(bank);
        card.setCardNumber(number);
        card.setUserId(userId);
        try {
            cardRepo.save(card);
        }catch (Exception e){

        }
        return card;
    }
}
