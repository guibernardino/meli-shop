package com.meli.shop.product.presentation.api.v1;

import com.meli.shop.product.application.dto.ProductDTO;
import com.meli.shop.product.application.service.ProductService;
import com.meli.shop.product.domain.exception.ProductNotFoundException;
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
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    @DisplayName("Should return product when found")
    void testGetProduct_success() throws Exception {
        ProductDTO dto = new ProductDTO(1L, "P", "desc", BigDecimal.TEN, 1L, List.of("img"), 5);
        Mockito.when(productService.getProductById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("P")))
                .andExpect(jsonPath("$.availableStock", is(5)));
    }

    @Test
    @DisplayName("Should return 404 when product not found")
    void testGetProduct_notFound() throws Exception {
        Mockito.when(productService.getProductById(2L)).thenThrow(new ProductNotFoundException(2L));

        mockMvc.perform(get("/api/v1/products/2"))
                .andExpect(status().isNotFound());
    }
}