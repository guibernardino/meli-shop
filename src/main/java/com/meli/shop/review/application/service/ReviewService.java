package com.meli.shop.review.application.service;

import com.meli.shop.review.application.dto.ProductRatingDTO;
import com.meli.shop.review.application.dto.ReviewDTO;
import com.meli.shop.review.domain.model.ProductRating;
import com.meli.shop.review.domain.model.Review;
import com.meli.shop.review.domain.repository.ProductRatingRepository;
import com.meli.shop.review.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRatingRepository productRatingRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            ProductRatingRepository productRatingRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.productRatingRepository = productRatingRepository;
    }

    public List<ReviewDTO> getReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(ReviewDTO::from)
                .toList();
    }

    public ProductRatingDTO getProductRating(Long productId) {
        return productRatingRepository.findByProductId(productId)
                .map(rating -> new ProductRatingDTO(rating.getAverageRating(), rating.getReviewsCount()))
                .orElse(new ProductRatingDTO(0.0, 0));
    }

    public void addReview(ReviewDTO dto) {
        Review review = new Review();
        review.setProductId(dto.productId());
        review.setRating(dto.rating());
        review.setDescription(dto.description());
        review.setImages(dto.images());

        reviewRepository.save(review);

        productRatingRepository.findByProductId(dto.productId()).ifPresentOrElse(
                existingRating -> {
                    double totalRating = existingRating.getAverageRating() * existingRating.getReviewsCount() + dto.rating();
                    int newCount = existingRating.getReviewsCount() + 1;
                    existingRating.setAverageRating(totalRating / newCount);
                    existingRating.setReviewsCount(newCount);

                    productRatingRepository.save(existingRating);
                },
                () -> {
                    ProductRating newRating = new ProductRating();
                    newRating.setProductId(dto.productId());
                    newRating.setAverageRating(dto.rating());
                    newRating.setReviewsCount(1);

                    productRatingRepository.save(newRating);
                }
        );
    }
}

