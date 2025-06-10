package com.meli.shop.seller.presentation.api.v1;

import com.meli.shop.seller.application.dto.SellerDTO;
import com.meli.shop.seller.application.service.SellerService;
import com.meli.shop.seller.domain.exception.SellerNotFoundException;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SellerController.class)
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SellerService sellerService;

    @Test
    @DisplayName("Should return seller when found")
    void testGetSeller_success() throws Exception {
        SellerDTO dto = new SellerDTO(1L, "John", "SP", 4.5);
        Mockito.when(sellerService.getSellerById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/sellers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.rating", is(4.5)));
    }

    @Test
    @DisplayName("Should return 404 when seller not found")
    void testGetSeller_notFound() throws Exception {
        Mockito.when(sellerService.getSellerById(2L)).thenThrow(new SellerNotFoundException(2L));

        mockMvc.perform(get("/api/v1/sellers/2"))
                .andExpect(status().isNotFound());
    }
}