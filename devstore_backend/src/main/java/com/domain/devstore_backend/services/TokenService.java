package com.domain.devstore_backend.services;

import com.domain.devstore_backend.entities.User;

public interface TokenService {

    String generateToken(User user);

    String validateToken(String token);
}
