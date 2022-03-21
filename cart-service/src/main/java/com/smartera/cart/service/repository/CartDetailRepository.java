package com.smartera.cart.service.repository;

import com.smartera.cart.service.entity.Cart;
import com.smartera.cart.service.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Integer> {

    List<CartDetail> getCartDetailsByCartId(Optional<Cart> cart);
}
