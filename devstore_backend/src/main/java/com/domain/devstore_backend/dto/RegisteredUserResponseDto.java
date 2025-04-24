package com.domain.devstore_backend.dto;

public record RegisteredUserResponseDto(
        Integer id,
        String name,
        String email) {
}
