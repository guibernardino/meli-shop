package com.meli.shop.review.application.dto;

import com.meli.shop.review.domain.model.Review;

import java.util.List;

public record ReviewDTO(
        Long id,
        Long productId,
        int rating,
        String description,
        List<String> images
) {
    public static ReviewDTO from(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getProductId(),
                review.getRating(),
                review.getDescription(),
                review.getImages()
        );
    }
}
