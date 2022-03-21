package com.smartera.customer.service.service;

import com.smartera.customer.service.dto.ReadCustomerDTO;
import com.smartera.customer.service.dto.ReadCustomerForCartDTO;
import com.smartera.customer.service.dto.WriteCustomerDTO;
import com.smartera.customer.service.entity.Customer;
import com.smartera.customer.service.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;


    ModelMapper modelMapper;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void init() {
        this.modelMapper = new ModelMapper();
    }


    @Test
    public void test_get_customer_by_id () {

        //GIVEN
        final var id = 1;
        Customer mockCustomer= new Customer(1,"furkan",true);
        when(customerRepository.getById(id))
                .thenReturn(mockCustomer);
        final var expectedCustomerReadDTO = modelMapper.map(mockCustomer, ReadCustomerForCartDTO.class);

        //WHEN
        final var customerReadDTO = customerService.getCustomerById(id);

        //THEN
        assertThat(customerReadDTO)
                .isEqualTo(expectedCustomerReadDTO);

        verify(customerRepository.getById(id));

    }

    @Test
    public void test_save_customer(){

        //GIVEN
        WriteCustomerDTO writeCustomerDto = new WriteCustomerDTO("furkan",true);
        final var expectedCustomer = modelMapper.map(writeCustomerDto,Customer.class);
        final var mockCustomer = new Customer(1,"furkan",true);
        ReadCustomerDTO expectedCustomerReadDto = modelMapper.map(mockCustomer, ReadCustomerDTO.class);

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(mockCustomer);

        //WHEN
        ReadCustomerDTO readCustomerDto = customerService.saveCustomer(writeCustomerDto);

        //THEN
        assertEquals(expectedCustomerReadDto,readCustomerDto);

        final var saveAC = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(saveAC.capture());
        assertThat(saveAC.getValue())
                .isEqualTo(expectedCustomer);

    }

    @Test
    public void test_get_all_customers() {
        //GIVEN
        Customer mockCustomer1 = new Customer(1, "furkan",true);
        Customer mockCustomer2 = new Customer(2, "ali",true);
        final var expectedCustomerRead1 = modelMapper.map(mockCustomer1, ReadCustomerDTO.class);
        final var expectedCustomerRead2 = modelMapper.map(mockCustomer2, ReadCustomerDTO.class);
        final var expectedCustomerReadDTOS = Arrays
                .asList(expectedCustomerRead1, expectedCustomerRead2);

        when(customerRepository.findAll())
                .thenReturn(Arrays.asList(mockCustomer1, mockCustomer2));

        //WHEN
        final var customers = customerService.getAllCustomers();

        //THEN
        assertThat(customers.get(0)).isEqualTo(expectedCustomerRead1);
        assertThat(customers.get(1)).isEqualTo(expectedCustomerRead2);

        IntStream.range(0, expectedCustomerReadDTOS.size()).forEach(index -> {
            assertThat(expectedCustomerReadDTOS.get(index)).isEqualTo(customers.get(index));
        });

        verify(customerRepository).findAll();
    }

    @Test
    public void test_delete_customer_by_id_if_is_present_customer(){

        //GIVEN
        Customer customer = new Customer(1,"furkan",true);
        Optional<Customer> customerOptional = Optional.of(customer);
        final var id =1;

        when(customerRepository.findById(1)).thenReturn(customerOptional);

        //WHEN
        final var expectedStatus = customerService.deleteCustomer(id);

        //THEN
        assertTrue(expectedStatus);
        verify(customerRepository,times(1)).deleteById(id);
    }

    @Test
    public void test_delete_customer_by_id_if_is_not_present_customer(){

        //GIVEN
        Customer mockCustomer = new Customer(1, "furkan",true);
        final var id =2;

        //WHEN
        final var expectedStatus =customerService.deleteCustomer(id);

        //THEN
        assertFalse(expectedStatus);
        verify(customerRepository,times(0)).deleteById(id);
    }

    @Test
    public void test_update_customer_if_is_present_customer(){

        //GIVEN
        final var id =1;
        Customer mockCustomer = new Customer(1, "furkan",true);
        Optional<Customer> customerOptional = Optional.of(mockCustomer);

        WriteCustomerDTO writeCustomerDto = new WriteCustomerDTO("ali",true);
        customerOptional.get().setName(writeCustomerDto.getName());
        customerOptional.get().setAuthority(writeCustomerDto.isAuthority());

        ReadCustomerDTO expectedReadCustomerCustomerDTO = modelMapper
                .map(customerOptional.get(),ReadCustomerDTO.class);

        when(customerRepository.findById(1)).thenReturn(customerOptional);
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        //WHEN
        final var readCustomerDTO = customerService.updateCustomer(id,writeCustomerDto);
        final var expectedCustomer = new Customer(1,"ali",true);

        //THEN
        assertEquals(expectedReadCustomerCustomerDTO,readCustomerDTO);

        final var updateAc = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(updateAc.capture());

        assertThat(updateAc.getValue())
                .isEqualTo(expectedCustomer);
        verify(customerRepository,times(1))
                .save(any(Customer.class));
    }


}
