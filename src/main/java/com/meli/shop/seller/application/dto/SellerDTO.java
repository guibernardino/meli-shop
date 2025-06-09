package com.meli.shop.seller.application.dto;

import com.meli.shop.seller.domain.model.Seller;

public record SellerDTO(
        Long id,
        String name,
        String location,
        double rating
) {
    public static SellerDTO from(Seller seller) {
        return new SellerDTO(
                seller.getId(),
                seller.getName(),
                seller.getLocation(),
                seller.getRating()
        );
    }
}
