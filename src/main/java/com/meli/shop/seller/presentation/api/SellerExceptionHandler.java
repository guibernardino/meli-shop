package com.meli.shop.seller.presentation.api;

import com.meli.shop.seller.domain.exception.SellerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages  = "com.meli.shop.seller")
public class SellerExceptionHandler {
    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<String> handleSellerNotFound(SellerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

