package com.sed.card_service.services;

import com.sed.card_service.dataaccess.DTO.CardDTO;

import java.util.List;

public interface CardService {
    public CardDTO registerCard(CardDTO cardDTO);
    public CardDTO removeCard(CardDTO cardDTO);
    public List<CardDTO> getUserCard(Long userId);

}
