package com.meli.shop.inventory.domain.repository;

import com.meli.shop.inventory.domain.model.Stock;

import java.util.Optional;

public interface StockRepository {
    Optional<Stock> findByProductId(Long productId);
    void save(Stock stock);
}
