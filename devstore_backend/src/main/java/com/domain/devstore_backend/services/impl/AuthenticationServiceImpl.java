package com.domain.devstore_backend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import com.domain.devstore_backend.entities.User;
import com.domain.devstore_backend.mapper.UserMapper;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.LoginResponseDto;
import com.domain.devstore_backend.services.TokenService;
import com.domain.devstore_backend.dto.AuthenticationDto;
import com.domain.devstore_backend.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devstore_backend.controllers.ProductController;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;
import com.domain.devstore_backend.services.AuthenticationService;
import com.domain.devstore_backend.exceptions.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.domain.devstore_backend.controllers.AuthenticationController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public EntityModel<LoginResponseDto> login(AuthenticationDto dto) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
        var token = tokenService.generateToken((User) auth.getPrincipal());
        var responseDto = new LoginResponseDto(
                dto.email(), token
        );
        return EntityModel.of(responseDto).add(linkTo(methodOn(ProductController.class)
                .findAll(null)).withRel("Find All"));
    }


    @Override
    @Transactional
    public EntityModel<RegisteredUserResponseDto> register(RegisterUserDto dto) {
        if (userRepository.findByEmail(dto.email()).isPresent())
            throw new BadRequestException("Email already exist");

        var user = UserMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);
        var responseDto = new RegisteredUserResponseDto(
                savedUser.getId(), savedUser.getName(), savedUser.getUsername()
        );
        return EntityModel.of(responseDto).add(linkTo(methodOn(AuthenticationController.class)
                .login(null)).withRel("login"));
    }
}
