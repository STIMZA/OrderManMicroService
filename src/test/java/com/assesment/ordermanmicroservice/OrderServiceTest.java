package com.assesment.ordermanmicroservice;

import com.assesment.ordermanmicroservice.dto.OrderRequest;
import com.assesment.ordermanmicroservice.exception.ResourceNotFoundException;
import com.assesment.ordermanmicroservice.model.Order;
import com.assesment.ordermanmicroservice.model.Product;
import com.assesment.ordermanmicroservice.repository.OrderRepository;
import com.assesment.ordermanmicroservice.repository.ProductRepository;
import com.assesment.ordermanmicroservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderService orderService;

    @Mock private OrderRepository orderRepository;
    @Mock private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    void testCreateOrder_Success() {
        // Arrange
        Product product = new Product(1L, "Laptop", new BigDecimal("1000.00"));
        OrderRequest.ItemLine line = new OrderRequest.ItemLine();
        line.setProductId(1L);
        line.setQuantity(2);

        OrderRequest request = new OrderRequest();
        request.setItems(Collections.singletonList(line));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order created = orderService.createOrder(request);

        // Assert
        assertNotNull(created);
        assertEquals(new BigDecimal("2000.00"), created.getTotalPrice());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testCreateOrder_ProductNotFound_ThrowsException() {
        // Arrange
        OrderRequest.ItemLine line = new OrderRequest.ItemLine();
        line.setProductId(99L);
        line.setQuantity(1);

        OrderRequest request = new OrderRequest();
        request.setItems(Collections.singletonList(line));

        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            orderService.createOrder(request);
        });

        assertTrue(exception.getMessage().contains("Order rejected: Product ID 99 does not exist"));
        verify(orderRepository, never()).save(any(Order.class));
    }
}
