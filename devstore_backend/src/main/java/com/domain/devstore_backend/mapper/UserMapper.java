package com.domain.devstore_backend.mapper;

import com.domain.devstore_backend.entities.User;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;

public class UserMapper {

    public static User toUser(RegisterUserDto dto) {
        return new User(dto.name(), dto.email(), dto.phone(), dto.birthDate(), dto.password(), dto.role());
    }


    public static RegisteredUserResponseDto toUserResponse(User user) {
        return new RegisteredUserResponseDto(user.getName(), user.getEmail());
    }
}
