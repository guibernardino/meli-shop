package com.meli.shop.bff.presentation.api.v1;

import com.meli.shop.bff.application.dto.ProductDetailResponse;
import com.meli.shop.bff.application.service.ProductQueryService;
import com.meli.shop.review.application.dto.ProductRatingDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductDetailController.class)
class ProductDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductQueryService productQueryService;

    @Test
    @DisplayName("Should return product detail")
    void testGetDetail() throws Exception {
        ProductDetailResponse.SellerInfo seller = new ProductDetailResponse.SellerInfo(1L, "S", "loc", 4.0);
        ProductDetailResponse resp = new ProductDetailResponse(1L, "P", "d", BigDecimal.TEN, List.of("img"), 2, seller, new ProductRatingDTO(4.0, 10));
        Mockito.when(productQueryService.getProductDetail(1L)).thenReturn(resp);

        mockMvc.perform(get("/api/v1/product-details/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.seller.id", is(1)))
                .andExpect(jsonPath("$.availableQuantity", is(2)));
    }
}