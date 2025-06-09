package com.meli.shop.seller.domain.repository;

import com.meli.shop.seller.domain.model.Seller;

import java.util.Optional;

public interface SellerRepository {
    Optional<Seller> findById(Long id);
}

