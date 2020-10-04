package com.sed.card_service.apiControllers;

import com.sed.card_service.dataaccess.DTO.CardDTO;
import com.sed.card_service.services.CardService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/card")
public class CardApi {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(CardApi.class);

    CardService cardService;

    @Autowired
    public CardApi(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<CardDTO> registerCard(@RequestBody CardDTO cardDTO) {
        CardDTO result = cardService.registerCard(cardDTO);
        return new ResponseEntity<CardDTO>(result, HttpStatus.OK);
    }

    //@DeleteMapping(path = "/remove")
    @DeleteMapping(path = "/remove")
    public ResponseEntity<CardDTO> removeCard(@RequestBody CardDTO cardDTO) {
        CardDTO result = cardService.removeCard(cardDTO);
        return new ResponseEntity<CardDTO>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllCard")
    public ResponseEntity<List<CardDTO>> getAllCard(@RequestParam Long userId) {
        List<CardDTO> result = cardService.getUserCard(userId);
        return new ResponseEntity<List<CardDTO>>(result, HttpStatus.OK);
    }
}
