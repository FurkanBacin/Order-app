package com.smartera.customer.service.service;

import com.smartera.customer.service.dto.ReadCustomerDTO;
import com.smartera.customer.service.dto.ReadCustomerForCartDTO;
import com.smartera.customer.service.dto.WriteCustomerDTO;
import com.smartera.customer.service.entity.Customer;
import com.smartera.customer.service.exception.CustomerApiRequestException;
import com.smartera.customer.service.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public ReadCustomerDTO saveCustomer(WriteCustomerDTO writeCustomerDto) {

        Customer customer = modelMapper.map(writeCustomerDto, Customer.class);
        return modelMapper.map(customerRepository.save(customer), ReadCustomerDTO.class);
    }

    @Override
    @Transactional
    public List<ReadCustomerDTO> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        List<ReadCustomerDTO> dtos = customers.stream().map(customer -> modelMapper.map(customer, ReadCustomerDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional
    public ReadCustomerForCartDTO getCustomerById(int id) {

        Customer customer = customerRepository.getById(id);
        return modelMapper.map(customer, ReadCustomerForCartDTO.class);
    }

    @Override
    @Transactional
    public boolean deleteCustomer(int id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public ReadCustomerDTO updateCustomer(int id, WriteCustomerDTO writeCustomerDto) {

        Optional<Customer> resultCustomer = customerRepository.findById(id);
        if (resultCustomer.isPresent()) {
            resultCustomer.get().setName(writeCustomerDto.getName());
            resultCustomer.get().setAuthority(writeCustomerDto.isAuthority());
            return modelMapper.map(customerRepository.save(resultCustomer.get()), ReadCustomerDTO.class);
        }
        throw new CustomerApiRequestException("Müşteri bulunamadı.");
    }

    @Override
    public boolean customerAuthority(int id) {

        Customer resultCustomer = customerRepository.getById(id);
        if(resultCustomer.isAuthority()){
            return true;
        }
        return false;
    }
}
