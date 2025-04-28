package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;
import com.domain.devstore_backend.entities.Product;
import com.domain.devstore_backend.services.ProductService;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devstore_backend.controllers.ProductController;
import com.domain.devstore_backend.exceptions.BadRequestException;
import com.domain.devstore_backend.repositories.ProductRepository;
import com.domain.devstore_backend.exceptions.ResourceNotFoundException;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<EntityModel<Product>> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> EntityModel.of(product, linkTo(methodOn(ProductController.class)
                .findById(product.getId())).withSelfRel()));
    }


    @Override
    @Transactional(readOnly = true)
    public EntityModel<Product> findById(Integer id) {
        idValidation(id);
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")
        );
        return EntityModel.of(product).add(linkTo(methodOn(ProductController.class)
                .findAll(null)).withRel("Find All"));
    }


    @Override
    @Transactional
    public EntityModel<Product> create(Product product) {
        if (productRepository.existsByName(product.getName())) throw new BadRequestException(
                "A product with this name already exists");
        var savedProduct = productRepository.save(product);
        return EntityModel.of(savedProduct)
                .add(linkTo(methodOn(ProductController.class).findAll(null)).withRel("Find All"))
                .add(linkTo(methodOn(ProductController.class).findById(savedProduct.getId())).withRel("Find By Id"));
    }


    @Override
    @Transactional
    public EntityModel<Product> update(Integer id, Product product) {
        idValidation(id);
        product.setId(id);
        var updatedProduct = productRepository.save(product);
        return EntityModel.of(updatedProduct).add(linkTo(methodOn(ProductController.class)
                .findById(updatedProduct.getId())).withRel("Find By Id"));
    }


    @Override
    @Transactional
    public EntityModel<?> delete(Integer id) {
        idValidation(id);
        productRepository.deleteById(id);
        Map<String, String> message = Map.of("message", "Deleted successfully");
        return EntityModel.of(message)
                .add(linkTo(methodOn(ProductController.class).findAll(null)).withRel("Find All"));
    }


    private void idValidation(Integer id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Id must be a positive number");
        if (!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
    }
}
