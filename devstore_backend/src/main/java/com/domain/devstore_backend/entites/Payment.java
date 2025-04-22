package com.domain.devstore_backend.entites;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

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
    private Long id;
    private Instant moment;

    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
