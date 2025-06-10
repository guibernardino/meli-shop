package com.meli.shop.review.application.service;

import com.meli.shop.review.application.dto.ProductRatingDTO;
import com.meli.shop.review.application.dto.ReviewDTO;
import com.meli.shop.review.domain.model.ProductRating;
import com.meli.shop.review.domain.model.Review;
import com.meli.shop.review.domain.repository.ProductRatingRepository;
import com.meli.shop.review.domain.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @Mock
    ReviewRepository reviewRepository;
    @Mock
    ProductRatingRepository ratingRepository;

    @InjectMocks
    ReviewService service;

    @Test
    void testGetReviewsForProduct() {
        Review review = new Review();
        review.setId(10L);
        review.setProductId(1L);
        review.setRating(4);
        review.setDescription("good");

        Mockito.when(reviewRepository.findByProductId(1L)).thenReturn(List.of(review));

        List<ReviewDTO> list = service.getReviewsForProduct(1L);
        assertEquals(1, list.size());
        assertEquals(10L, list.get(0).id());
        assertEquals(4, list.get(0).rating());
    }

    @Test
    void testGetProductRating_whenExists() {
        ProductRating rating = new ProductRating();
        rating.setProductId(1L);
        rating.setAverageRating(4.5);
        rating.setReviewsCount(10);
        Mockito.when(ratingRepository.findByProductId(1L)).thenReturn(Optional.of(rating));

        ProductRatingDTO dto = service.getProductRating(1L);

        assertEquals(4.5, dto.averageRating());
        assertEquals(10, dto.reviewsCount());
    }

    @Test
    void testGetProductRating_whenMissing() {
        Mockito.when(ratingRepository.findByProductId(1L)).thenReturn(Optional.empty());
        ProductRatingDTO dto = service.getProductRating(1L);
        assertEquals(0.0, dto.averageRating());
        assertEquals(0, dto.reviewsCount());
    }

    @Test
    void testAddReview_updatesExistingRating() {
        ReviewDTO dto = new ReviewDTO(null, 1L, 5, "nice", List.of());

        ProductRating existing = new ProductRating();
        existing.setProductId(1L);
        existing.setAverageRating(4.0);
        existing.setReviewsCount(2);

        Mockito.when(ratingRepository.findByProductId(1L)).thenReturn(Optional.of(existing));

        service.addReview(dto);

        Mockito.verify(reviewRepository).save(Mockito.any(Review.class));
        ArgumentCaptor<ProductRating> captor = ArgumentCaptor.forClass(ProductRating.class);
        Mockito.verify(ratingRepository).save(captor.capture());
        ProductRating saved = captor.getValue();
        assertEquals(1L, saved.getProductId());
        assertEquals(3, saved.getReviewsCount());
        assertEquals((4.0 * 2 + 5) / 3, saved.getAverageRating());
    }

    @Test
    void testAddReview_createsNewRatingWhenMissing() {
        ReviewDTO dto = new ReviewDTO(null, 2L, 3, "ok", List.of());
        Mockito.when(ratingRepository.findByProductId(2L)).thenReturn(Optional.empty());

        service.addReview(dto);

        Mockito.verify(reviewRepository).save(Mockito.any(Review.class));
        ArgumentCaptor<ProductRating> captor = ArgumentCaptor.forClass(ProductRating.class);
        Mockito.verify(ratingRepository).save(captor.capture());
        ProductRating saved = captor.getValue();
        assertEquals(2L, saved.getProductId());
        assertEquals(1, saved.getReviewsCount());
        assertEquals(3.0, saved.getAverageRating());
    }
}