package com.meli.shop.seller.application.service;

import com.meli.shop.seller.application.dto.SellerDTO;
import com.meli.shop.seller.domain.exception.SellerNotFoundException;
import com.meli.shop.seller.domain.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public SellerDTO getSellerById(Long id) {
        return sellerRepository.findById(id)
                .map(SellerDTO::from)
                .orElseThrow(() -> new SellerNotFoundException(id));
    }
}

