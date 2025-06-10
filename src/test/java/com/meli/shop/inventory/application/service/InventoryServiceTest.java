package com.meli.shop.inventory.application.service;

import com.meli.shop.inventory.application.dto.StockDTO;
import com.meli.shop.inventory.domain.exception.StockInsufficientException;
import com.meli.shop.inventory.domain.exception.StockUnavailableException;
import com.meli.shop.inventory.domain.model.Stock;
import com.meli.shop.inventory.domain.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    @Mock
    StockRepository repository;

    @InjectMocks
    InventoryService service;

    @Test
    void testGetStock_success() {
        Stock stock = new Stock(1L, 5);
        Mockito.when(repository.findByProductId(1L)).thenReturn(Optional.of(stock));
        StockDTO dto = service.getStock(1L);
        assertEquals(5, dto.quantity());
    }

    @Test
    void testGetStock_unavailable() {
        Mockito.when(repository.findByProductId(1L)).thenReturn(Optional.empty());
        StockDTO dto = service.getStock(1L);
        assertEquals(0, dto.quantity());
    }

    @Test
    void testDebitStock_success() {
        Stock stock = new Stock(1L, 5);
        Mockito.when(repository.findByProductId(1L)).thenReturn(Optional.of(stock));
        service.debitStock(1L, 3);
        assertEquals(2, stock.getQuantity());
        Mockito.verify(repository).save(stock);
    }

    @Test
    void testDebitStock_insufficient() {
        Stock stock = new Stock(1L, 2);
        Mockito.when(repository.findByProductId(1L)).thenReturn(Optional.of(stock));
        assertThrows(StockInsufficientException.class, () -> service.debitStock(1L, 3));
    }

    @Test
    void testRestock_newStock() {
        Mockito.when(repository.findByProductId(1L)).thenReturn(Optional.empty());
        service.restock(1L, 4);
        ArgumentCaptor<Stock> captor = ArgumentCaptor.forClass(Stock.class);
        Mockito.verify(repository).save(captor.capture());
        Stock saved = captor.getValue();
        assertEquals(1L, saved.getProductId());
        assertEquals(4, saved.getQuantity());
    }
}