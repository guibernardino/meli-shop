package com.meli.shop.review.domain.repository;

import com.meli.shop.review.domain.model.ProductRating;

import java.util.Optional;

public interface ProductRatingRepository {
    Optional<ProductRating> findByProductId(Long productId);
    void save(ProductRating rating);
}
