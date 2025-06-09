package com.meli.shop.review.infrastucture.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.shop.review.domain.model.ProductRating;
import com.meli.shop.review.domain.repository.ProductRatingRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class JsonProductRatingRepository implements ProductRatingRepository {

    private final Map<Long, ProductRating> ratings = new ConcurrentHashMap<>();

    public JsonProductRatingRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("data/product_ratings.json").getInputStream()) {
            ProductRating[] ratingsArray = mapper.readValue(is, ProductRating[].class);
            for (var rating : ratingsArray) {
                ratings.put(rating.getProductId(), rating);
            }
        }
    }

    @Override
    public Optional<ProductRating> findByProductId(Long productId) {
        return Optional.ofNullable(ratings.get(productId));
    }

    @Override
    public void save(ProductRating rating) {
        ratings.put(rating.getProductId(), rating);
    }
}

