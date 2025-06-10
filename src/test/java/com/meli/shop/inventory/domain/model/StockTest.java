package com.meli.shop.inventory.domain.model;

import com.meli.shop.inventory.domain.exception.StockInsufficientException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    @Test
    void testHasAvailableAndDebit() {
        Stock stock = new Stock(1L, 10);
        assertTrue(stock.hasAvailable(5));
        stock.debit(5);
        assertEquals(5, stock.getQuantity());
    }

    @Test
    void testDebitInsufficient() {
        Stock stock = new Stock(1L, 2);
        assertThrows(StockInsufficientException.class, () -> stock.debit(3));
        assertEquals(2, stock.getQuantity());
    }

    @Test
    void testRestock() {
        Stock stock = new Stock(1L, 1);
        stock.restock(4);
        assertEquals(5, stock.getQuantity());
    }
}