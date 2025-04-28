package com.domain.devstore_backend.controllers;

import com.domain.devstore_backend.entities.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.domain.devstore_backend.dto.ProductDto;
import org.springframework.data.web.PageableDefault;
import com.domain.devstore_backend.mapper.ProductMapper;
import com.domain.devstore_backend.services.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Objects;


@Tag(name = "Product")
@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping
    @Operation(summary = "Obter listagem", description = "Obtenção de lista de produtos")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Produtos obtidos com sucesso"))
    public ResponseEntity<Page<EntityModel<ProductDto>>> findAll(@PageableDefault(size = 6) Pageable pageable) {
        Page<EntityModel<Product>> productEntityList = productService.findAll(pageable);
        Page<EntityModel<ProductDto>> productDtoList = productEntityList.map(entityModel -> {
            var productDto = ProductMapper.toDto(Objects.requireNonNull(entityModel.getContent()));
            return EntityModel.of(productDto, entityModel.getLinks());
        });
        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Obter por id", description = "Obtenção por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<EntityModel<ProductDto>> findById(@PathVariable Integer id) {
        EntityModel<Product> productEntity = productService.findById(id);
        var productDto = ProductMapper.toDto(Objects.requireNonNull(productEntity.getContent()));
        return ResponseEntity.status(HttpStatus.OK).body(EntityModel.of(productDto, productEntity.getLinks()));
    }


    @PostMapping
    @Operation(summary = "Inserir", description = "Inserção de produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "409", description = "Produto já cadastrado"),
            @ApiResponse(responseCode = "201", description = "Cadastro feito com sucesso")
    })
    public ResponseEntity<EntityModel<ProductDto>> create(@Valid @RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        EntityModel<Product> productEntity = productService.create(product);
        var productDto = ProductMapper.toDto(Objects.requireNonNull(productEntity.getContent()));
        return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.of(productDto, productEntity.getLinks()));
    }


    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar", description = "Atualização por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "204", description = "Atualização feito com sucesso")}
    )
    public ResponseEntity<EntityModel<ProductDto>> update(@PathVariable Integer id, @Valid @RequestBody ProductDto dto) {
        var product = ProductMapper.toProduct(dto);
        EntityModel<Product> productEntity = productService.update(id, product);
        var productDto = ProductMapper.toDto(Objects.requireNonNull(productEntity.getContent()));
        return ResponseEntity.status(HttpStatus.OK).body(EntityModel.of(productDto, productEntity.getLinks()));
    }


    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar", description = "Deleção por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")}
    )
    public ResponseEntity<EntityModel<?>> delete(@PathVariable Integer id) {
        EntityModel<?> productEntity = productService.delete(id);
        return ResponseEntity.ok(productEntity);
    }
}
