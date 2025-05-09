package com.domain.devstore_backend.entities;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }
}
