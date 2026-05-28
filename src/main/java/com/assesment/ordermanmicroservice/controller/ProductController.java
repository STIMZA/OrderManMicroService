package com.assesment.ordermanmicroservice.controller;

import com.assesment.ordermanmicroservice.model.Product;
import com.assesment.ordermanmicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "basicAuth")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product product) {
        log.info("Incoming HTTP POST request to create product: {}", product.getName());
        return service.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        log.info("Incoming HTTP GET request for product ID: {}", id);
        return service.getProductById(id);
    }

    @GetMapping
    public List<Product> list() {
        log.info("Incoming HTTP GET request to list all products");
        return service.listAllProducts();
    }
}