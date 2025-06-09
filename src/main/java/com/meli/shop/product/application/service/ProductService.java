package com.meli.shop.product.application.service;

import com.meli.shop.product.application.dto.ProductDTO;
import com.meli.shop.product.domain.exception.ProductNotFoundException;
import com.meli.shop.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::from)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
