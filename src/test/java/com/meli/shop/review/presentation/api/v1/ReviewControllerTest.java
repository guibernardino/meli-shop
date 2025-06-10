package com.meli.shop.review.presentation.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.shop.review.application.dto.ProductRatingDTO;
import com.meli.shop.review.application.dto.ReviewDTO;
import com.meli.shop.review.application.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReviewService reviewService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Should list reviews")
    void testGetReviews() throws Exception {
        ReviewDTO dto = new ReviewDTO(1L, 1L, 5, "good", List.of("img"));
        Mockito.when(reviewService.getReviewsForProduct(1L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/reviews/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rating", is(5)));
    }

    @Test
    @DisplayName("Should return product rating")
    void testGetRating() throws Exception {
        ProductRatingDTO rating = new ProductRatingDTO(4.0, 10);
        Mockito.when(reviewService.getProductRating(2L)).thenReturn(rating);

        mockMvc.perform(get("/api/v1/reviews/product/2/rating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageRating", is(4.0)))
                .andExpect(jsonPath("$.reviewsCount", is(10)));
    }

    @Test
    @DisplayName("Should add review")
    void testAddReview() throws Exception {
        ReviewDTO dto = new ReviewDTO(null, 3L, 4, "ok", List.of());

        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        ArgumentCaptor<ReviewDTO> captor = ArgumentCaptor.forClass(ReviewDTO.class);
        Mockito.verify(reviewService).addReview(captor.capture());
        assertEquals(3L, captor.getValue().productId());
    }
}
