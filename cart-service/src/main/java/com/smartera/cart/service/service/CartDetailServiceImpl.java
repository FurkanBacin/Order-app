package com.smartera.cart.service.service;

import com.smartera.cart.service.dto.CartDetailDTO;
import com.smartera.cart.service.dto.ReadCartDetailDTO;
import com.smartera.cart.service.dto.ReadProductForCartDTO;
import com.smartera.cart.service.dto.WriteCartDetailDTO;
import com.smartera.cart.service.entity.Cart;
import com.smartera.cart.service.entity.CartDetail;
import com.smartera.cart.service.exception.CartApiRequestException;
import com.smartera.cart.service.repository.CartDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CartDetailServiceImpl implements CartDetailService{

    private final CartDetailRepository cartDetailRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final CartServiceImpl cartService;
    private final ProductApiService productApiService;

    public CartDetailServiceImpl(CartDetailRepository cartDetailRepository,
                                 CartServiceImpl cartService,
                                 ProductApiService productApiService) {

        this.cartDetailRepository = cartDetailRepository;
        this.cartService = cartService;
        this.productApiService = productApiService;
    }

    @Override
    @Transactional
    public CartDetailDTO saveCartDetail(WriteCartDetailDTO writeCartDetailDto) {

        CartDetail cartDetail = modelMapper.map(writeCartDetailDto,CartDetail.class);
        return modelMapper.map(cartDetailRepository.save(cartDetail), CartDetailDTO.class);
    }

    @Override
    @Transactional
    public ReadCartDetailDTO getCartDetailWithProduct(int id) {

        ReadCartDetailDTO readCartDetailDto = new ReadCartDetailDTO();
        CartDetail cartDetail = cartDetailRepository.getById(id);
        CartDetailDTO cartDetailDto = modelMapper.map(cartDetail, CartDetailDTO.class);
        ReadProductForCartDTO readProductForCartDto = productApiService.getProductById(cartDetail.getProductId());

        readCartDetailDto.setCartDetail(cartDetailDto);
        readCartDetailDto.setProduct(readProductForCartDto);

        return readCartDetailDto;

    }

    @Override
    public CartDetailDTO getCartDetail(int id) {

        CartDetail cartDetail = cartDetailRepository.getById(id);

        return modelMapper.map(cartDetail, CartDetailDTO.class);

    }

    @Override
    @Transactional
    public boolean deleteCartDetailById(int id) {

        Optional<CartDetail> cartDetail = cartDetailRepository.findById(id);

        if (cartDetail.isPresent()){
            cartDetailRepository.deleteById(id);
            return true;
        }
        throw new CartApiRequestException("Geçerli sepet detayı bulunamamaktadır");
    }

    @Override
    public List<CartDetail> getAllCartDetail(int id) {

        Optional<Cart> cart = cartService.getCartForDetail(id);

        return  cartDetailRepository.getCartDetailsByCartId(cart);

    }

}
