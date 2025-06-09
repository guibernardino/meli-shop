package com.meli.shop.product.application.dto;

import com.meli.shop.product.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(
        Long id,
        String title,
        String description,
        BigDecimal price,
        Long sellerId,
        List<String> images,
        int availableStock
) {
    public static ProductDTO from(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getSellerId(),
                product.getImages(),
                product.getAvailableStock()
        );
    }
}
