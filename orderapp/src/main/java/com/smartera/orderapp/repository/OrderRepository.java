package com.smartera.orderapp.repository;

import com.smartera.orderapp.model.Customer;
import com.smartera.orderapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findOrderByCustomer(Optional<Customer> customer);
}
