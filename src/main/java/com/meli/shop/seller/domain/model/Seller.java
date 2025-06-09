package com.meli.shop.seller.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seller {
    private Long id;
    private String name;
    private String location;
    private double rating;
}
