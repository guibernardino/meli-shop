package com.meli.shop.bff.application.service;

import com.meli.shop.bff.application.dto.ProductDetailResponse;
import com.meli.shop.inventory.application.dto.StockDTO;
import com.meli.shop.inventory.application.service.InventoryService;
import com.meli.shop.product.application.dto.ProductDTO;
import com.meli.shop.product.application.service.ProductService;
import com.meli.shop.review.application.dto.ProductRatingDTO;
import com.meli.shop.review.application.service.ReviewService;
import com.meli.shop.seller.application.dto.SellerDTO;
import com.meli.shop.seller.application.service.SellerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductQueryServiceTest {
    @Mock
    ProductService productService;
    @Mock
    SellerService sellerService;
    @Mock
    ReviewService reviewService;
    @Mock
    InventoryService inventoryService;

    @InjectMocks
    ProductQueryService service;

    @Test
    void testGetProductDetail() {
        ProductDTO product = new ProductDTO(1L, "P", "desc", BigDecimal.TEN, 1L, List.of("img"), 0);
        SellerDTO seller = new SellerDTO(1L, "Seller", "loc", 4.0);
        ProductRatingDTO rating = new ProductRatingDTO(4.5, 20);
        StockDTO stock = new StockDTO(1L, 5);

        Mockito.when(productService.getProductById(1L)).thenReturn(product);
        Mockito.when(sellerService.getSellerById(1L)).thenReturn(seller);
        Mockito.when(reviewService.getProductRating(1L)).thenReturn(rating);
        Mockito.when(inventoryService.getStock(1L)).thenReturn(stock);

        ProductDetailResponse resp = service.getProductDetail(1L);

        assertEquals("P", resp.title());
        assertEquals(5, resp.availableQuantity());
        assertEquals(1L, resp.seller().id());
        assertEquals(4.0, resp.seller().reputation());
        assertEquals(4.5, resp.rating().averageRating());
    }
}