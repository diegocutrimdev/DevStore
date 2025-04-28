package com.domain.devstore_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;
import com.domain.devstore_backend.entities.Product;

public interface ProductService {

    Page<EntityModel<Product>> findAll(Pageable pageable);

    EntityModel<Product> findById(Integer id);

    EntityModel<Product> create(Product product);

    EntityModel<Product> update(Integer id, Product product);

    EntityModel<?> delete(Integer id);
}
