package com.domain.devstore_backend.dto;

import com.domain.devstore_backend.entities.UserRole;

import java.time.LocalDate;

public record RegisterUserDto(
        String name,
        String email,
        String phone,
        LocalDate birthDate,
        String password,
        UserRole role) {
}

