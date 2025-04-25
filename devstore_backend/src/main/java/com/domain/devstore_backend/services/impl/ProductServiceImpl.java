package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.domain.devstore_backend.entities.Product;
import com.domain.devstore_backend.services.ProductService;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devstore_backend.exceptions.BadRequestException;
import com.domain.devstore_backend.repositories.ProductRepository;
import com.domain.devstore_backend.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        idValidation(id);
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }


    @Override
    @Transactional
    public Product create(Product product) {
        if (productRepository.existsByName(product.getName()))
            throw new BadRequestException("A product with this name already exists");
        return productRepository.save(product);
    }


    @Override
    @Transactional
    public Product update(Integer id, Product product) {
        idValidation(id);
        product.setId(id);
        return productRepository.save(product);
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        idValidation(id);
        productRepository.deleteById(id);
    }


    private void idValidation(Integer id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Id must be a positive number");
        if (!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
    }
}
