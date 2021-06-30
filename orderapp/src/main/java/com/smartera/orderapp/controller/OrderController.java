package com.smartera.orderapp.controller;

import com.smartera.orderapp.model.Order;
import com.smartera.orderapp.service.OrderService;
import com.smartera.orderapp.utilities.result.DataResult;
import com.smartera.orderapp.utilities.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAllOrders")
    @ApiOperation(value=" it will fetch all order")
    public DataResult<List<Order>> getAllOrder(){

        return orderService.getAllOrder();

    }
    @PostMapping("/createOrder")
    @ApiOperation(value="creates an order")
    public Result createOrder(@RequestBody Order order) {

        return orderService.createOrder(order);
    }

    @GetMapping("/getOrderByCustomerId/{id}")
    @ApiOperation(value="brings the customer's order")
    public DataResult<List<Order>> getOrderByCustomerId(@PathVariable int id){

        return orderService.getOrderByCustomerId(id);
    }

    @DeleteMapping("/deleteOrder/{id}")
    @ApiOperation(value=" it will delete order with id")
    public Result deleteOrder(@PathVariable int id){

        return orderService.deleteOrderById(id);
    }

    @PutMapping("/updateOrder")
    @ApiOperation(value="updates order")
    public Result updateOrderById(@RequestBody Order order) {

        return orderService.updateOrderById(order);
    }
}
