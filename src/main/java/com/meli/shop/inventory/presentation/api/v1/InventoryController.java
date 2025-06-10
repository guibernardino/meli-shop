package com.meli.shop.inventory.presentation.api.v1;

import com.meli.shop.inventory.application.dto.StockDTO;
import com.meli.shop.inventory.application.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Operation(summary = "Get stock", description = "Retrieves current stock for a product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock retrieved"),
            @ApiResponse(responseCode = "404", description = "Stock unavailable", content = @Content)
    })
    @GetMapping("/{productId}")
    public ResponseEntity<StockDTO> getStock(
            @Parameter(description = "Product identifier", required = true) @PathVariable Long productId
    ) {
        StockDTO stock = inventoryService.getStock(productId);
        return ResponseEntity.ok(stock);
    }
}

