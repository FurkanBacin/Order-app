package com.smartera.customer.service.controller;

import com.smartera.customer.service.dto.ReadCustomerDTO;
import com.smartera.customer.service.dto.ReadCustomerForCartDTO;
import com.smartera.customer.service.dto.WriteCustomerDTO;
import com.smartera.customer.service.service.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save-customer")
    @ApiOperation(value=" it is saving new customer")
    public ResponseEntity<ReadCustomerDTO> saveCustomer(@RequestBody WriteCustomerDTO writeCustomerDto) {
        ReadCustomerDTO resultCustomer = customerService.saveCustomer(writeCustomerDto);
        return new ResponseEntity<>(resultCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/all-customers")
    @ApiOperation(value=" it will fetch all customer")
    public ResponseEntity<List<ReadCustomerDTO>> getAllCustomer(){

        List<ReadCustomerDTO> readCustomerDTOList = customerService.getAllCustomers();
        return new ResponseEntity<>(readCustomerDTOList,HttpStatus.OK);
    }

    @GetMapping("/get-customer-by-id/{id}")
    @ApiOperation(value=" it will fetch customer")
    public ResponseEntity<ReadCustomerForCartDTO> getCustomerById(@PathVariable int id){

        ReadCustomerForCartDTO readCustomerForCartDto = customerService.getCustomerById(id);
        return new ResponseEntity<>(readCustomerForCartDto,HttpStatus.OK);
    }
    @GetMapping("/authority/{id}")
    public ResponseEntity<Boolean> getAuthority(@PathVariable int id){

        Boolean status = customerService.customerAuthority(id);
        return new ResponseEntity<>(status,HttpStatus.OK);

    }

    @DeleteMapping("/delete-customer/{id}")
    @ApiOperation(value=" it will delete customer with id")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable int id){

        Boolean status = customerService.deleteCustomer(id);
        return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update-customer/{id}")
    @ApiOperation(value=" it will update customer")
    public ResponseEntity<ReadCustomerDTO> updateCustomer(@PathVariable int id, @RequestBody WriteCustomerDTO writeCustomerDto) {

        ReadCustomerDTO resultCustomer = customerService.updateCustomer(id,writeCustomerDto);
        return new ResponseEntity<>(resultCustomer,HttpStatus.OK);
    }
}
