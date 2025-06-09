package com.meli.shop.review.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.shop.review.domain.model.Review;
import com.meli.shop.review.domain.repository.ReviewRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JsonReviewRepository implements ReviewRepository {
    private final List<Review> reviews;

    public JsonReviewRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new ClassPathResource("data/reviews.json").getInputStream()) {
            reviews = new ArrayList<>(Arrays.asList(mapper.readValue(is, Review[].class)));
        }
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return reviews.stream()
                .filter(r -> r.getProductId().equals(productId))
                .toList();
    }

    @Override
    public void save(Review review) {
        if (review.getId() == null) {
            review.setId(generateNewId());
        }
        reviews.add(review);
    }

    private Long generateNewId() {
        return System.currentTimeMillis();
    }
}

