package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.domain.devstore_backend.entities.Product;
import com.domain.devstore_backend.services.ProductService;
import com.domain.devstore_backend.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Product findById(Integer id) {
        idValidation(id);
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
    }


    @Override
    public Product create(Product product) {
        if (productRepository.existsByName(product.getName()))
            throw new IllegalArgumentException("A product with this name already exists");
        return productRepository.save(product);
    }


    @Override
    public Product update(Integer id, Product product) {
        idValidation(id);
        product.setId(id);
        return productRepository.save(product);
    }


    @Override
    public void delete(Integer id) {
        idValidation(id);
        productRepository.deleteById(id);
    }


    private void idValidation(Integer id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Id must be a positive number");
        if (!productRepository.existsById(id))
            throw new RuntimeException("Product with ID " + id + " not found");
    }
}
