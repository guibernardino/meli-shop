package com.meli.shop.seller.application.service;

import com.meli.shop.seller.application.dto.SellerDTO;
import com.meli.shop.seller.domain.exception.SellerNotFoundException;
import com.meli.shop.seller.domain.model.Seller;
import com.meli.shop.seller.domain.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {
    @Mock
    SellerRepository repository;

    @InjectMocks
    SellerService service;

    @Test
    void testGetSellerById_success() {
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setName("John");
        seller.setLocation("SP");
        seller.setRating(4.5);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(seller));

        SellerDTO dto = service.getSellerById(1L);

        assertEquals(1L, dto.id());
        assertEquals("John", dto.name());
        assertEquals("SP", dto.location());
        assertEquals(4.5, dto.rating());
    }

    @Test
    void testGetSellerById_notFound() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(SellerNotFoundException.class, () -> service.getSellerById(1L));
    }
}