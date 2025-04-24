package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.domain.devstore_backend.mapper.UserMapper;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;
import com.domain.devstore_backend.services.AuthenticationService;
import com.domain.devstore_backend.exceptions.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisteredUserResponseDto register(RegisterUserDto dto) {
        if (userRepository.findByEmail(dto.email()) != null)
            throw new BadRequestException("Email already exist");

        var user = UserMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }
}
