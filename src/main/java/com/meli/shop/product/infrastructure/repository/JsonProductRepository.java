package com.meli.shop.product.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.shop.product.domain.model.Product;
import com.meli.shop.product.domain.repository.ProductRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JsonProductRepository implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    public JsonProductRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("data/products.json").getInputStream()) {
            Product[] productsArray = mapper.readValue(is, Product[].class);
            for (var product : productsArray) {
                products.put(product.getId(), product);
            }
        }
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }
}

