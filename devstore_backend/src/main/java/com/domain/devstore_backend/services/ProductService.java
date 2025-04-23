package com.domain.devstore_backend.services;

import com.domain.devstore_backend.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product create(Product product);

    Product update(Integer id, Product product);

    void delete(Integer id);
}
