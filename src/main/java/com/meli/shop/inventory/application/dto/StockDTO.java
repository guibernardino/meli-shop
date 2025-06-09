package com.meli.shop.inventory.application.dto;

import com.meli.shop.inventory.domain.model.Stock;

public record StockDTO(
        Long productId,
        int quantity
) {
    public static StockDTO from(Stock stock) {
        return new StockDTO(stock.getProductId(), stock.getQuantity());
    }
}
