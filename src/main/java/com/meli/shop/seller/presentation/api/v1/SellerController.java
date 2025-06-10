package com.meli.shop.seller.presentation.api.v1;

import com.meli.shop.seller.application.dto.SellerDTO;
import com.meli.shop.seller.application.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Operation(summary = "Get seller", description = "Retrieves seller information by id")
    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSeller(
            @Parameter(description = "Seller identifier", required = true) @PathVariable Long id
    ) {
        return ResponseEntity.ok(sellerService.getSellerById(id));
    }
}

