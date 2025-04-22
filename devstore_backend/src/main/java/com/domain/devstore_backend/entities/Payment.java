package com.domain.devstore_backend.entities;

import lombok.*;
import jakarta.persistence.*;

import java.time.Instant;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant moment;

    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
