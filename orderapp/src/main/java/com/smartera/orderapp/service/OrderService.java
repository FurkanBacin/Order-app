package com.smartera.orderapp.service;

import com.smartera.orderapp.model.Customer;
import com.smartera.orderapp.model.Order;
import com.smartera.orderapp.repository.OrderRepository;
import com.smartera.orderapp.utilities.result.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository
            , CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    public DataResult<List<Order>> getAllOrder() {

        return new SuccessDataResult<>(orderRepository.findAll(), "Data Listed");
    }

    public Result createOrder(Order order) {
        if(order.getCustomer().isAuthority()){

            orderRepository.save(order);
            return new SuccessResult("Order created");
        }
        else{
            return new ErrorResult("this customer is not authorized to order ");
        }
    }

    public DataResult<List<Order>> getOrderByCustomerId(int id) {

        Optional<Customer> customer = customerService.findCustomerById(id);
        return new SuccessDataResult<>(orderRepository.findOrderByCustomer(customer),"Data Listed");
    }

    public Result deleteOrderById(int id) {

        orderRepository.deleteById(id);
        return new SuccessResult("Order Deleted");
    }

    public Result updateOrderById(Order order) {

        orderRepository.save(order);
        return new SuccessResult("Order updated");
    }
}
