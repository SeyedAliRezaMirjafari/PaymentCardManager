package com.sed.payment_service.apiControllers;

import com.sed.payment_service.dataaccess.DTO.TransactionDTO;
import com.sed.payment_service.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/transfer")
public class TransferCard {

    TransactionService transactionService;

    @Autowired
    public TransferCard(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/card")
    public ResponseEntity<TransactionDTO> transferCard(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO result = transactionService.transferCard(transactionDTO);
        return new ResponseEntity<TransactionDTO>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/report/{userId}")
    public ResponseEntity<String> transferReport(@PathVariable("userId") Long id,@RequestParam String start,@RequestParam String end) {
        String result = transactionService.getTransactionReport(id,start,end);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
