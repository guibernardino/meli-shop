package com.meli.shop.product.application.service;

import com.meli.shop.product.application.dto.ProductDTO;
import com.meli.shop.product.domain.exception.ProductNotFoundException;
import com.meli.shop.product.domain.model.Product;
import com.meli.shop.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Test
    void testGetProductById_success() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));

        ProductDTO dto = service.getProductById(1L);

        assertEquals(1L, dto.id());
        assertEquals("Produto Teste", dto.title());
    }

    @Test
    void testGetProductById_notFound() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> service.getProductById(1L));
    }
}
