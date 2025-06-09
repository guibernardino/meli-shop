package com.meli.shop.seller.domain.exception;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(Long id) {
        super("Seller not found: " + id);
    }
}