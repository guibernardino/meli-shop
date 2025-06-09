package com.meli.shop.product.domain.repository;

import com.meli.shop.product.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}
