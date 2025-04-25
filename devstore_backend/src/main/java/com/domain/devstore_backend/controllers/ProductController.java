package com.domain.devstore_backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.domain.devstore_backend.dto.ProductDto;
import com.domain.devstore_backend.mapper.ProductMapper;
import com.domain.devstore_backend.services.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(name = "Product")
@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    @Operation(summary = "Obter listagem", description = "Obtenção de lista de produtos")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Produtos obtidos com sucesso"))
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productDtoList = ProductMapper.toListDto(productService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter por id", description = "Obtenção por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
        var product = productService.findById(id);
        var productDto = ProductMapper.toDto(product);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @PostMapping
    @Operation(summary = "Inserir", description = "Inserção de produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "409", description = "Produto já cadastrado"),
            @ApiResponse(responseCode = "201", description = "Cadastro feito com sucesso")
    })
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        var savedProduct = productService.create(product);
        var productDto = ProductMapper.toDto(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }


    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar", description = "Atualização por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "204", description = "Atualização feito com sucesso")}
    )
    public ResponseEntity<ProductDto> update(@PathVariable Integer id, @Valid @RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        var updatedProduct = productService.update(id, product);
        var productDto = ProductMapper.toDto(updatedProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar", description = "Deleção por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")}
    )
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
