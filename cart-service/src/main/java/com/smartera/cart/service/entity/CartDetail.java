package com.smartera.cart.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="cart_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cartId;

    @Column(name="product_id")
    private int productId;

    @Column(name="quantity")
    private int quantity;
}
