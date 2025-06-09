package com.meli.shop.inventory.presentation.api;

import com.meli.shop.inventory.domain.exception.StockInsufficientException;
import com.meli.shop.inventory.domain.exception.StockUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages  = "com.meli.shop.inventory")
public class InventoryExceptionHandler {

    @ExceptionHandler(StockInsufficientException.class)
    public ResponseEntity<String> handleStockInsufficient(StockInsufficientException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(StockUnavailableException.class)
    public ResponseEntity<String> handleStockUnavailable(StockUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
