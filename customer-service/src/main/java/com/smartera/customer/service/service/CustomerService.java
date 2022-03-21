package com.smartera.customer.service.service;

import com.smartera.customer.service.dto.ReadCustomerDTO;
import com.smartera.customer.service.dto.ReadCustomerForCartDTO;
import com.smartera.customer.service.dto.WriteCustomerDTO;

import java.util.List;

public interface CustomerService {

    ReadCustomerDTO saveCustomer (WriteCustomerDTO writeCustomerDto);
    List<ReadCustomerDTO> getAllCustomers ();
    ReadCustomerForCartDTO getCustomerById(int id);
    boolean deleteCustomer(int id);
    ReadCustomerDTO updateCustomer(int id, WriteCustomerDTO writeCustomerDto);
    boolean customerAuthority(int id);
}
