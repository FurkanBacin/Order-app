package com.smartera.orderapp.service;

import com.smartera.orderapp.dto.CustomerDto;
import com.smartera.orderapp.dto.converter.CustomerConverter;
import com.smartera.orderapp.model.Customer;
import com.smartera.orderapp.repository.CustomerRepository;
import com.smartera.orderapp.utilities.result.DataResult;
import com.smartera.orderapp.utilities.result.Result;
import com.smartera.orderapp.utilities.result.SuccessDataResult;
import com.smartera.orderapp.utilities.result.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    public DataResult<List<Customer>> getAllCustomer() {

        return new SuccessDataResult<>(customerRepository.findAll());
    }

    protected Optional<Customer> findCustomerById(int id){

        return customerRepository.findById(id);
    }

    public DataResult<CustomerDto> getCustomerById(int id){
        Customer customer = customerRepository.getById(id);
        return new SuccessDataResult<>(customerConverter.entityToDto(customer),"Data Listed");
    }


    public Result saveCustomer(Customer customer) {

        customerRepository.save(customer);

        return new SuccessResult("Customer added");
    }

    public Result deleteCustomerById(int id) {

        customerRepository.deleteById(id);

        return new SuccessResult("Customer deleted");
    }

    public Result updateCustomerById(Customer customer) {

        customerRepository.save(customer);

        return new SuccessResult("Customer updated");
    }

}
