package com.meli.shop.inventory.domain.model;

import com.meli.shop.inventory.domain.exception.StockInsufficientException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stock {
    private Long productId;
    private int quantity;

    public boolean hasAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    public void debit(int qty) {
        if (!hasAvailable(qty)) {
            throw new StockInsufficientException(productId, qty, quantity);
        }
        quantity -= qty;
    }

    public void restock(int qty) {
        quantity += qty;
    }
}
