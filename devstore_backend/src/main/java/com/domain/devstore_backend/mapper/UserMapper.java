package com.domain.devstore_backend.mapper;

import com.domain.devstore_backend.dto.RegisterDto;
import com.domain.devstore_backend.entities.User;

public class UserMapper {

    public static User toUser(RegisterDto dto) {
        return new User(dto.name(), dto.email(), dto.phone(), dto.birthDate(), dto.password(), dto.role());
    }
}
