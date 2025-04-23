package com.domain.devstore_backend.entities;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("admin"), USER("user");

    private final String role;
}
