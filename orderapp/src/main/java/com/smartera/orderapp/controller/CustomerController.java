package com.smartera.orderapp.controller;

import com.smartera.orderapp.dto.CustomerDto;
import com.smartera.orderapp.model.Customer;
import com.smartera.orderapp.service.CustomerService;
import com.smartera.orderapp.utilities.result.DataResult;
import com.smartera.orderapp.utilities.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/saveCustomer")
    @ApiOperation(value=" it is saving new customer")
    public Result saveCustomer(@RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }

    @GetMapping("/getAllCustomers")
    @ApiOperation(value=" it will fetch all customer")
    public DataResult<List<Customer>> getAllCustomer(){

        return customerService.getAllCustomer();
    }

    @GetMapping("/getCustomerById/{id}")
    @ApiOperation(value=" it will fetch customer")
    public DataResult<CustomerDto> getCustomerById(@PathVariable int id){

        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @ApiOperation(value=" it will delete customer with id")
    public Result deleteCustomerById(@PathVariable int id ){

        return customerService.deleteCustomerById(id);
    }

    @PutMapping("/updateCustomer")
    @ApiOperation(value=" it will update customer")
    public Result updateCustomerById(@RequestBody Customer customer) {

        return customerService.updateCustomerById(customer);
    }
}
