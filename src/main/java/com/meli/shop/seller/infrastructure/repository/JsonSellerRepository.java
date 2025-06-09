package com.meli.shop.seller.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.shop.seller.domain.model.Seller;
import com.meli.shop.seller.domain.repository.SellerRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JsonSellerRepository implements SellerRepository {

    private final Map<Long, Seller> sellers = new ConcurrentHashMap<>();

    public JsonSellerRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("data/sellers.json").getInputStream()) {
            Seller[] sellersArray = mapper.readValue(is, Seller[].class);
            for (var seller : sellersArray) {
                sellers.put(seller.getId(), seller);
            }
        }
    }

    @Override
    public Optional<Seller> findById(Long sellerId) {
        return Optional.ofNullable(sellers.get(sellerId));
    }
}

