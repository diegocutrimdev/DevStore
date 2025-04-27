package com.domain.devstore_backend.services;

import org.springframework.hateoas.EntityModel;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.LoginResponseDto;
import com.domain.devstore_backend.dto.AuthenticationDto;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;

public interface AuthenticationService {

    EntityModel<LoginResponseDto> login(AuthenticationDto dto);

    EntityModel<RegisteredUserResponseDto> register(RegisterUserDto dto);
}
