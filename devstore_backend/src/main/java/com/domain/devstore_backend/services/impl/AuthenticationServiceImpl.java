package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.domain.devstore_backend.entities.User;
import com.domain.devstore_backend.mapper.UserMapper;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.LoginResponseDto;
import com.domain.devstore_backend.services.TokenService;
import com.domain.devstore_backend.dto.AuthenticationDto;
import com.domain.devstore_backend.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;
import com.domain.devstore_backend.services.AuthenticationService;
import com.domain.devstore_backend.exceptions.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResponseDto login(AuthenticationDto dto) {
        var usernameAndPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernameAndPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDto(usernameAndPassword.getName(), token);
    }


    @Override
    @Transactional
    public RegisteredUserResponseDto register(RegisterUserDto dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) throw new BadRequestException("Email already exist");
        var user = UserMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }
}
