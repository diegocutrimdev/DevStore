package com.domain.devstore_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.domain.devstore_backend.dto.ProductDto;
import com.domain.devstore_backend.mapper.ProductMapper;
import com.domain.devstore_backend.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        var savedProduct = productService.create(product);
        var productDto = ProductMapper.toDto(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }
}
