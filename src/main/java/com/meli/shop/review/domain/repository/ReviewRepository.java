package com.meli.shop.review.domain.repository;

import com.meli.shop.review.domain.model.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findByProductId(Long productId);
    void save(Review review);
}

