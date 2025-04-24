package com.domain.devstore_backend.services;

import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;

public interface AuthenticationService {
    RegisteredUserResponseDto register(RegisterUserDto dto);
}
