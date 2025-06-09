package com.meli.shop.inventory.presentation.api.v1;

import com.meli.shop.inventory.application.dto.StockDTO;
import com.meli.shop.inventory.application.service.InventoryService;
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

    @GetMapping("/{productId}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long productId) {
        StockDTO stock = inventoryService.getStock(productId);
        return ResponseEntity.ok(stock);
    }
}

