package com.domain.devstore_backend.services;

import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.LoginResponseDto;
import com.domain.devstore_backend.dto.AuthenticationDto;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;

public interface AuthenticationService {

    LoginResponseDto login(AuthenticationDto dto);

    RegisteredUserResponseDto register(RegisterUserDto dto);
}
