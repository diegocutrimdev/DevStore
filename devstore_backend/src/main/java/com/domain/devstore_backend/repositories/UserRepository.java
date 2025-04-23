package com.domain.devstore_backend.repositories;

import com.domain.devstore_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByEmail(String login);
}
