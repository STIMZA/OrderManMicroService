package com.assesment.ordermanmicroservice.service;

import com.assesment.ordermanmicroservice.dto.OrderRequest;
import com.assesment.ordermanmicroservice.exception.ResourceNotFoundException;
import com.assesment.ordermanmicroservice.model.Order;
import com.assesment.ordermanmicroservice.model.OrderItem;
import com.assesment.ordermanmicroservice.model.Product;
import com.assesment.ordermanmicroservice.repository.OrderRepository;
import com.assesment.ordermanmicroservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order createOrder(OrderRequest request) {
        log.info("Processing order placement event for {} items", request.getItems().size());
        BigDecimal calculatedTotal = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderRequest.ItemLine line : request.getItems()) {
            Product product = productRepository.findById(line.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order rejected: Product ID " +
                            line.getProductId() + " does not exist"));

            BigDecimal linePrice = product.getPrice().multiply(BigDecimal.valueOf(line.getQuantity()));
            calculatedTotal = calculatedTotal.add(linePrice);

            orderItems.add(OrderItem.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(line.getQuantity())
                    .price(linePrice)
                    .build());
        }

        Order order = Order.builder()
                .totalPrice(calculatedTotal)
                .items(orderItems)
                .build();

        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully with ID: {} | Total Price: {}",
                savedOrder.getId(), savedOrder.getTotalPrice());
        return savedOrder;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with ID " + id + " not found"));
    }

    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }
}