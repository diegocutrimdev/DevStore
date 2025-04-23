package com.domain.devstore_backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.domain.devstore_backend.dto.ProductDto;
import com.domain.devstore_backend.mapper.ProductMapper;
import com.domain.devstore_backend.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productDtoList = ProductMapper.toListDto(productService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
        var product = productService.findById(id);
        var productDto = ProductMapper.toDto(product);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        var savedProduct = productService.create(product);
        var productDto = ProductMapper.toDto(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer id, @Valid @RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        var updatedProduct = productService.update(id, product);
        var productDto = ProductMapper.toDto(updatedProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
