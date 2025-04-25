package com.domain.devstore_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(name = "Product")
public record ProductDto(

        @Null(message = "ID must be null on creation")
        Integer id,

        @Schema(name = "name")
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        BigDecimal price,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,

        @Schema(name = "image_url")
        @NotBlank(message = "Image URL is required")
        @Size(max = 255, message = "Image URL must not exceed 255 characters")
        String image
) {
}
