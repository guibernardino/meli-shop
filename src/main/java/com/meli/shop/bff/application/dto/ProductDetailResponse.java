package com.meli.shop.bff.application.dto;

import com.meli.shop.review.application.dto.ProductRatingDTO;

import java.math.BigDecimal;
import java.util.List;

public record ProductDetailResponse(
        Long id,
        String title,
        String description,
        BigDecimal price,
        List<String> images,
        int availableQuantity,
        SellerInfo seller,
        ProductRatingDTO rating
) {
    public record SellerInfo(
            Long id,
            String name,
            String location,
            double reputation
    ) {}
}
