package com.meli.shop.review.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRating {
    private Long productId;
    private double averageRating;
    private int reviewsCount;
}
