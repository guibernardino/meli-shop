package com.meli.shop.bff.presentation.api.v1;

import com.meli.shop.bff.application.service.ProductQueryService;
import com.meli.shop.bff.application.dto.ProductDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-details")
public class ProductDetailController {

    private final ProductQueryService productQueryService;

    public ProductDetailController(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @Operation(summary = "Get product detail", description = "Retrieves product detail aggregating multiple services")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> getDetail(
            @Parameter(description = "Product identifier", required = true) @PathVariable Long productId
    ) {
        return ResponseEntity.ok(productQueryService.getProductDetail(productId));
    }
}

