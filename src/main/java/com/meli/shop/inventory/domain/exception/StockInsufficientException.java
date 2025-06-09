package com.meli.shop.inventory.domain.exception;

public class StockInsufficientException extends RuntimeException {
    public StockInsufficientException(Long productId, int requested, int available) {
        super("Estoque insuficiente para o produto " + productId +
                ": solicitado " + requested + ", disponível " + available);
    }
}
