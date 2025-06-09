package com.meli.shop.review.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Review {
    private Long id;
    private Long productId;
    private int rating;
    private String description;
    private List<String> images;
}
