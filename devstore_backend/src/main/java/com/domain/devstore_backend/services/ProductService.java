package com.domain.devstore_backend.services;

import com.domain.devstore_backend.dto.ProductDto;
import com.domain.devstore_backend.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product create(Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}
