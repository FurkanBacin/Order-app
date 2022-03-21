package com.smartera.cart.service.service;

import com.smartera.cart.service.client.CustomerServiceClient;
import com.smartera.cart.service.dto.*;
import com.smartera.cart.service.entity.Cart;
import com.smartera.cart.service.repository.CartRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @InjectMocks
    CartServiceImpl cartService;

    @Mock
    CartRepository cartRepository;

    @Mock
    CustomerApiService customerApiService;
    
    ModelMapper modelMapper;
    
    @BeforeEach
    void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void test_save_to_cart_if_customer_has_authorization(){

        //GIVEN
        WriteCartDTO writeCartDto = new WriteCartDTO(1);
        final var expectedCart = modelMapper.map(writeCartDto, Cart.class);
        final var mockCart = new Cart(1,1);
        ReadCustomerForCartDTO readCustomerForCartDto = new ReadCustomerForCartDTO("furkan",true);
        CartDTO expectedCartDTO = new CartDTO(1,1);

        when(cartRepository.save(any(Cart.class))).thenReturn(mockCart);
        when(customerApiService.getCustomerById(mockCart.getCustomerId()))
                .thenReturn(readCustomerForCartDto);

        //WHEN
        CartDTO cartDTO = cartService.saveCart(writeCartDto);

        //THEN
        assertEquals(expectedCartDTO,cartDTO);

        final var saveAC = ArgumentCaptor.forClass(Cart.class);
        verify(cartRepository).save(saveAC.capture());
        assertThat(saveAC.getValue())
                .isEqualTo(expectedCart);


    }
    @Test
    public void test_get_cart(){

        //GIVEN
        final var id =1;
        ResponseTemplateDTO expectedResponseTemplateDto = new ResponseTemplateDTO();
        Cart mockCart = new Cart(1,1);
        ReadCartDTO readCartDto= modelMapper.map(mockCart,ReadCartDTO.class);
        ReadCustomerForCartDTO readCustomerForCartDto = new ReadCustomerForCartDTO("furkan",true);

        when(cartRepository.getById(id)).thenReturn(mockCart);
        when(customerApiService.getCustomerById(mockCart.getCustomerId())).thenReturn(readCustomerForCartDto);

        expectedResponseTemplateDto.setCart(readCartDto);
        expectedResponseTemplateDto.setCustomer(readCustomerForCartDto);

        //WHEN
        final var responseTemplateDTO = cartService.getCart(id);


        //THEN
        assertThat(responseTemplateDTO).isEqualTo(expectedResponseTemplateDto);

        verify(cartRepository).getById(id);

    }
    @Test
    public void test_get_cart_by_id(){

        //GIVEN
        final var id =1;
        Cart mockCart = new Cart(1,1);
        ReadCartDTO expectedReadCartDTO = modelMapper.map(mockCart,ReadCartDTO.class);

        when(cartRepository.getById(id)).thenReturn(mockCart);

        //WHEN
        final var readCartDto = cartService.getCartById(id);

        //THEN
        assertThat(readCartDto).isEqualTo(expectedReadCartDTO);

        verify(cartRepository).getById(id);

    }
    @Test
    public void test_delete_cart_if_cart_exists(){

        //GIVEN
        final var id =1;
        Cart mockCart = new Cart(1,1);
        Optional<Cart> cartOptional = Optional.of(mockCart);

        when(cartRepository.findById(id)).thenReturn(cartOptional);

        //WHEN
        final var status= cartService.deleteCartById(id);

        //THEN
        assertTrue(status);
    }

}
