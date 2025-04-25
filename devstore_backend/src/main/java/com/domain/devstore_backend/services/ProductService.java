package com.domain.devstore_backend.services;

import com.domain.devstore_backend.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);

    Product create(Product product);

    Product update(Integer id, Product product);

    void delete(Integer id);
}
