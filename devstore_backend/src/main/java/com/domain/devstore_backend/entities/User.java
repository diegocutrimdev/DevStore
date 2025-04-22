package com.domain.devstore_backend.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;
}
