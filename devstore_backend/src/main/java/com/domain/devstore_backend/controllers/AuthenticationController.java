package com.domain.devstore_backend.controllers;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.LoginResponseDto;
import com.domain.devstore_backend.dto.AuthenticationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;
import com.domain.devstore_backend.services.AuthenticationService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@Tag(name = "Authentication")

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping(value = "/login")
    @Operation(summary = "Autenticar", description = "Login de usuário com retorno de token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "400", description = "Requisição mal formatada")
    })
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationDto dto) {
        var login = authenticationService.login(dto);
        return ResponseEntity.ok().body(login);
    }


    @PostMapping(value = "/register")
    @Operation(summary = "Registrar", description = "Criação de conta de usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Usuário já existente")
    })
    public ResponseEntity<RegisteredUserResponseDto> register(@RequestBody RegisterUserDto dto) {
        var savedUser = authenticationService.register(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.id()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }
}
