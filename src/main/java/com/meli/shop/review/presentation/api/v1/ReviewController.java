package com.meli.shop.review.presentation.api.v1;

import com.meli.shop.review.application.dto.ProductRatingDTO;
import com.meli.shop.review.application.dto.ReviewDTO;
import com.meli.shop.review.application.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "List reviews", description = "Returns all reviews for the given product")
    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getReviews(
            @Parameter(description = "ID of the product", required = true) @PathVariable Long productId
    ) {
        return reviewService.getReviewsForProduct(productId);
    }

    @Operation(summary = "Get product rating", description = "Returns average rating for the given product")
    @GetMapping("/product/{productId}/rating")
    public ProductRatingDTO getRating(
            @Parameter(description = "ID of the product", required = true) @PathVariable Long productId
    ) {
        return reviewService.getProductRating(productId);
    }

    @Operation(summary = "Add review", description = "Adds a new review to the system")
    @PostMapping
    public void addReview(@RequestBody ReviewDTO dto) {
        reviewService.addReview(dto);
    }
}
