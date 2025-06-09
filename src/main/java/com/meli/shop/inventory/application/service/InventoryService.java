package com.meli.shop.inventory.application.service;

import com.meli.shop.inventory.application.dto.StockDTO;
import com.meli.shop.inventory.domain.exception.StockUnavailableException;
import com.meli.shop.inventory.domain.model.Stock;
import com.meli.shop.inventory.domain.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final StockRepository stockRepository;

    public InventoryService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockDTO getStock(Long productId) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockUnavailableException(productId));
        return StockDTO.from(stock);
    }

    public void debitStock(Long productId, int quantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockUnavailableException(productId));
        stock.debit(quantity);
        stockRepository.save(stock);
    }

    public void restock(Long productId, int quantity) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElse(new Stock(productId, 0));
        stock.restock(quantity);
        stockRepository.save(stock);
    }
}

