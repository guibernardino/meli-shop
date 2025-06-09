package com.meli.shop.review.application.dto;

public record ProductRatingDTO(
        double averageRating,
        int reviewsCount
) {}
