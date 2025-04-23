package com.domain.devstore_backend.dto;

import java.util.List;
import java.time.Instant;

public record ValidationError(
        Instant timestamp,
        Integer status,
        String error,
        String path,
        List<FieldMessage> errors) {
}
