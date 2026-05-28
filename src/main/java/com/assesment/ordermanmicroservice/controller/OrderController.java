package com.assesment.ordermanmicroservice.controller;

import com.assesment.ordermanmicroservice.dto.OrderRequest;
import com.assesment.ordermanmicroservice.model.Order;
import com.assesment.ordermanmicroservice.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "basicAuth")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order create(@Valid @RequestBody OrderRequest request) {
        log.info("Incoming HTTP POST request to create an order");
        return service.createOrder(request);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        log.info("Incoming HTTP GET request for order ID: {}", id);
        return service.getOrderById(id);
    }

    @GetMapping
    public List<Order> list() {
        log.info("Incoming HTTP GET request to list all orders");
        return service.listAllOrders();
    }
}
