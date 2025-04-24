package com.domain.devstore_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.domain.devstore_backend.dto.RegisterUserDto;
import com.domain.devstore_backend.dto.AuthenticationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.domain.devstore_backend.dto.RegisteredUserResponseDto;
import com.domain.devstore_backend.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;


    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationDto dto) {
        var usernameAndPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernameAndPassword);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/register")
    public ResponseEntity<RegisteredUserResponseDto> register(@RequestBody RegisterUserDto dto) {
        var savedUser = authenticationService.register(dto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.id())
                .toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }
}
