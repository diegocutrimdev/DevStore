package com.domain.devstore_backend.mapper;

import org.springframework.data.domain.Page;
import com.domain.devstore_backend.dto.ProductDto;
import com.domain.devstore_backend.entities.Product;

import java.util.List;

public class ProductMapper {

    public static Product toProduct(ProductDto dto) {
        return Product.builder()
                .id(dto.id())
                .name(dto.title())
                .price(dto.price())
                .description(dto.description())
                .imgUrl(dto.image())
                .build();
    }


    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImgUrl()
        );
    }


    public static Page<ProductDto> toListDto(Page<Product> products) {
        return products.map(ProductMapper::toDto);
    }


    public static List<Product> toListProduct(List<ProductDto> productDtoList) {
        return productDtoList.stream().map(ProductMapper::toProduct).toList();
    }
}
