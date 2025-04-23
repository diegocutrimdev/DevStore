package com.domain.devstore_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.domain.devstore_backend.dto.RegisterDto;
import com.domain.devstore_backend.mapper.UserMapper;
import com.domain.devstore_backend.dto.AuthenticationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.domain.devstore_backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthenticationController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationDto dto) {
        var usernameAndPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernameAndPassword);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterDto dto) {
        if (userRepository.findByEmail(dto.email()) != null) return ResponseEntity.badRequest().build();
        var encryptedPassword = passwordEncoder.encode(dto.password());
        var newUser = UserMapper.toUser(dto);
        newUser.setPassword(encryptedPassword);
        var savedUser = userRepository.save(newUser);
        return ResponseEntity.ok().body(savedUser);
    }
}
