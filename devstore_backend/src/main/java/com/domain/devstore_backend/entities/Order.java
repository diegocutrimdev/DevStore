package com.domain.devstore_backend.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment paymentMoment;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> items = new ArrayList<>();

    public List<Product> getProduct() {
        return items.stream().map(OrderItem::getProduct).toList();
    }
}
