package com.meli.shop.inventory.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.shop.inventory.domain.model.Stock;
import com.meli.shop.inventory.domain.repository.StockRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JsonStockRepository implements StockRepository {

    private final Map<Long, Stock> stockMap = new ConcurrentHashMap<>();

    public JsonStockRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("data/stock.json").getInputStream()) {
            Stock[] stocks = mapper.readValue(is, Stock[].class);
            for (Stock stock : stocks) {
                stockMap.put(stock.getProductId(), stock);
            }
        }
    }

    @Override
    public Optional<Stock> findByProductId(Long productId) {
        return Optional.ofNullable(stockMap.get(productId));
    }

    @Override
    public void save(Stock stock) {
        stockMap.put(stock.getProductId(), stock);
    }
}

