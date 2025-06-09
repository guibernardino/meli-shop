package com.meli.shop.review.presentation.api.v1;

import com.meli.shop.review.application.dto.ProductRatingDTO;
import com.meli.shop.review.application.dto.ReviewDTO;
import com.meli.shop.review.application.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getReviews(@PathVariable Long productId) {
        return reviewService.getReviewsForProduct(productId);
    }

    @GetMapping("/product/{productId}/rating")
    public ProductRatingDTO getRating(@PathVariable Long productId) {
        return reviewService.getProductRating(productId);
    }

    @PostMapping
    public void addReview(@RequestBody ReviewDTO dto) {
        reviewService.addReview(dto);
    }
}
