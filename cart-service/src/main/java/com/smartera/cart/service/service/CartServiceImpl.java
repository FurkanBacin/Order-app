package com.smartera.cart.service.service;

import com.smartera.cart.service.client.CustomerServiceClient;
import com.smartera.cart.service.client.ProductServiceClient;
import com.smartera.cart.service.dto.*;
import com.smartera.cart.service.entity.Cart;
import com.smartera.cart.service.exception.CartApiRequestException;
import com.smartera.cart.service.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final RestTemplateService restService;
    private final ProductServiceClient productServiceClient;
    private final CustomerServiceClient customerServiceClient;
    private final CustomerApiService customerApiService;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductServiceClient productServiceClient,
                           CustomerServiceClient customerServiceClient,
                           RestTemplateService restService,
                           ProductServiceClient productServiceClient1,
                           CustomerServiceClient customerServiceClient1,
                           CustomerApiService customerApiService) {

        this.cartRepository = cartRepository;
        this.restService = restService;
        this.productServiceClient = productServiceClient1;
        this.customerServiceClient = customerServiceClient1;
        this.customerApiService = customerApiService;
    }

    @Override
    @Transactional
    public CartDTO saveCart(WriteCartDTO writeCartDto) {

        Cart cart = modelMapper.map(writeCartDto,Cart.class);

        ReadCustomerForCartDTO readCustomerForCartDto = customerApiService.getCustomerById(cart.getCustomerId());

        if(readCustomerForCartDto.isAuthority()){
            return modelMapper.map(cartRepository.save(cart), CartDTO.class);
        }
        else {
            throw new CartApiRequestException("Müşterinin sipariş yetkisi bulunamamaktadır.");
        }
    }

    @Override
    @Transactional
    public ResponseTemplateDTO getCart(int id){

        ResponseTemplateDTO dto = new ResponseTemplateDTO();
        Cart cart= cartRepository.getById(id);
        ReadCartDTO readCartDto =modelMapper.map(cart, ReadCartDTO.class);

        ReadCustomerForCartDTO readCustomerDto= customerApiService.getCustomerById(cart.getCustomerId());

        dto.setCart(readCartDto);
        dto.setCustomer(readCustomerDto);
        return dto;

    }

    @Override
    @Transactional
    public ReadCartDTO getCartById(int id){

        Cart cart = cartRepository.getById(id);
        return modelMapper.map(cart, ReadCartDTO.class);
    }

    @Override
    @Transactional
    public Boolean deleteCartById(int id) {

        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isPresent()) {
            cartRepository.deleteById(id);
            return true;
        }
        throw new CartApiRequestException("Geçerli sepet bulunamamaktadır");
    }

    protected Optional<Cart> getCartForDetail(int id) {

        return cartRepository.findById(id);
    }


}
