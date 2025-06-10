package com.meli.shop.product.presentation.api.v1;

import com.meli.shop.product.application.dto.ProductDTO;
import com.meli.shop.product.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get product", description = "Retrieves product information by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(
            @Parameter(description = "Product identifier", required = true) @PathVariable Long id
    ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}

