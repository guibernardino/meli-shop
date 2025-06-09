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
import org.springframework.stereotype.Service;

@Service
public class ProductQueryService {

    private final ProductService productService;
    private final SellerService sellerService;
    private final ReviewService reviewService;
    private final InventoryService inventoryService;

    public ProductQueryService(
            ProductService productService,
            SellerService sellerService,
            ReviewService reviewService,
            InventoryService inventoryService
    ) {
        this.productService = productService;
        this.sellerService = sellerService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        ProductDTO product = productService.getProductById(productId);
        SellerDTO seller = sellerService.getSellerById(product.sellerId());
        ProductRatingDTO rating = reviewService.getProductRating(productId);
        StockDTO stock = inventoryService.getStock(product.id());

        return new ProductDetailResponse(
                product.id(),
                product.title(),
                product.description(),
                product.price(),
                product.images(),
                stock.quantity(),
                new ProductDetailResponse.SellerInfo(
                        seller.id(),
                        seller.name(),
                        seller.location(),
                        seller.rating()
                ),
                rating
        );
    }
}
