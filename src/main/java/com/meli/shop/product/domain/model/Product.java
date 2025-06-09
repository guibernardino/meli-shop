package com.meli.shop.product.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long sellerId;
    private List<String> images;
    private int availableStock;
}