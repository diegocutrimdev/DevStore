package com.domain.devstore_backend.dto;

import java.math.BigDecimal;

public record ProductDto(
        Integer id,
        String title,
        BigDecimal price,
        String description,
        String image
) {
}
