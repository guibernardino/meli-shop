package com.meli.shop.inventory.domain.exception;

public class StockUnavailableException extends RuntimeException {
    public StockUnavailableException(Long productId) {
        super("Estoque não disponível para o produto: " + productId);
    }
}
