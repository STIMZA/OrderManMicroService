package com.assesment.ordermanmicroservice.service;

import com.assesment.ordermanmicroservice.exception.ResourceNotFoundException;
import com.assesment.ordermanmicroservice.model.Product;
import com.assesment.ordermanmicroservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
    }

    public List<Product> listAllProducts() {
        return repository.findAll();
    }
}