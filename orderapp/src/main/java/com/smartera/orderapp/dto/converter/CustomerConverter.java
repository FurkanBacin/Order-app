package com.smartera.orderapp.dto.converter;

import com.smartera.orderapp.dto.CustomerDto;
import com.smartera.orderapp.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    public CustomerDto entityToDto(Customer customer){
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setAuthority(customer.isAuthority());
        return dto;
    }

    public List<CustomerDto> entityToDto(List<Customer> customer){
        return customer.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }

    public Customer dtoToEntity(CustomerDto dto){
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setAuthority(dto.isAuthority());
        return customer;
    }
    public List<Customer> dtoToEntity(List<CustomerDto> dto){
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

}
