package com.paulfernandosr.account.infrastructure.exception;

import com.paulfernandosr.account.domain.exception.AccountNotFoundException;
import com.paulfernandosr.account.domain.exception.CustomerNotFoundException;
import com.paulfernandosr.account.domain.exception.InvalidAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAmountException(InvalidAmountException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Saldo no disponible", HttpStatus.CONFLICT.value(), LocalDateTime.now()));
    }
}
