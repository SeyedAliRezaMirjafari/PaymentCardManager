package com.sed.card_service.apiControllers.exceptionHandler;

import com.sed.card_service.apiControllers.CardApi;
import com.sed.card_service.common.exceptions.ExistsRecordException;
import com.sed.card_service.common.exceptions.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(basePackageClasses = CardApi.class)
public class CardExceptionHandler {
    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(CardExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<String> NotFoundExceptionException
            (NotFoundException ex, WebRequest request) {
        logger.error("data not found " + ex.getMessage());
        return new ResponseEntity<>("data not found " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ExistsRecordException.class)
    public final ResponseEntity<String> ExistsRecordException
            (ExistsRecordException ex, WebRequest request) {
        logger.error("data existe "+ex.getMessage());
        return new ResponseEntity<>("data existe "+ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
