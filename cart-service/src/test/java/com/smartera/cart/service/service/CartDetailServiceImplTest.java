package com.smartera.cart.service.service;

import com.smartera.cart.service.dto.*;
import com.smartera.cart.service.entity.Cart;
import com.smartera.cart.service.entity.CartDetail;
import com.smartera.cart.service.repository.CartDetailRepository;

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
public class CartDetailServiceImplTest {

    @InjectMocks
    CartDetailServiceImpl cartDetailService;

    @Mock
    CartServiceImpl cartService;

    @Mock
    CartDetailRepository cartDetailRepository;

    @Mock
    ProductApiService productApiService;

    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void test_save_cart_detail(){

        //GIVEN
        WriteCartForCartDetailDTO writeCartForCartDetailDTO = new WriteCartForCartDetailDTO(1);
        WriteCartDetailDTO writeCartDetailDTO = new WriteCartDetailDTO(writeCartForCartDetailDTO,1,50);
        CartDetail mockCartDetail = modelMapper.map(writeCartDetailDTO,CartDetail.class);
        CartDetailDTO expectedCartDetailDTO =modelMapper.map(mockCartDetail,CartDetailDTO.class);

        when(cartDetailRepository.save(any(CartDetail.class))).thenReturn(mockCartDetail);

        //WHEN
        final var cartDetailDTO = cartDetailService.saveCartDetail(writeCartDetailDTO);

        //THEN
        assertEquals(expectedCartDetailDTO,cartDetailDTO);

        final var saveAC = ArgumentCaptor.forClass(CartDetail.class);
        verify(cartDetailRepository).save(saveAC.capture());
        assertThat(saveAC.getValue()).isEqualTo(mockCartDetail);
    }

    @Test
    public void test_get_cart_detail_with_product(){

        //GIVEN
        final var id =1;
        ReadCartDetailDTO expectedReadCartDetailDTO = new ReadCartDetailDTO();
        Cart mockCart = new Cart(1,1);
        CartDetail mockCartDetail = new CartDetail(1,mockCart,1,50);
        CartDetailDTO cartDetailDTO = modelMapper.map(mockCartDetail,CartDetailDTO.class);
        ReadProductForCartDTO readProductForCartDTO = new ReadProductForCartDTO("elma");

        when(cartDetailRepository.getById(id)).thenReturn(mockCartDetail);

        when(productApiService.getProductById(mockCartDetail.getProductId()))
                .thenReturn(readProductForCartDTO);

        expectedReadCartDetailDTO.setCartDetail(cartDetailDTO);
        expectedReadCartDetailDTO.setProduct(readProductForCartDTO);

        //WHEN
        final var readCartDetailDTO = cartDetailService.getCartDetailWithProduct(id);

        //THEN
        assertThat(readCartDetailDTO).isEqualTo(expectedReadCartDetailDTO);

        verify(cartDetailRepository).getById(id);

    }

    @Test
    public void test_get_cart_detail_by_id(){

        //GIVEN
        final var id =1;
        Cart mockCart = new Cart(1,1);
        CartDetail mockCartDetail = new CartDetail(1,mockCart,1,50);
        CartDetailDTO expectedCartDetailDTO = modelMapper.map(mockCartDetail,CartDetailDTO.class);

        when(cartDetailRepository.getById(id)).thenReturn(mockCartDetail);

        //WHEN
        final var cartDetailDTO = cartDetailService.getCartDetail(id);

        //THEN
        assertThat(cartDetailDTO).isEqualTo(expectedCartDetailDTO);

        verify(cartDetailRepository).getById(id);

    }
    @Test
    public void test_delete_cart_detail_if_cart_detail_exists(){

        //GIVEN
        final var id = 1;
        Cart mockCart = new Cart(1,1);
        CartDetail mockCartDetail = new CartDetail(1,mockCart,1,50);
        Optional<CartDetail> optionalCartDetail = Optional.of(mockCartDetail);

        when(cartDetailRepository.findById(id)).thenReturn(optionalCartDetail);

        //WHEN
        final var status = cartDetailService.deleteCartDetailById(id);

        //THEN
        assertTrue(status);
        verify(cartDetailRepository).findById(id);
    }

/* cart service de sıkıntı var gibi iletişim kurulmuyor
    @Test
    public void test_get_cart_details(){

        //GIVEN
        final var id= 1;
        Cart mockCart = new Cart(1,1);
        Optional<Cart> optionalCart =Optional.of(mockCart);
        Optional<Cart> expectedOptionalCart = cartService.getCartForDetail(id);
        List<CartDetail> cartDetailList = new ArrayList<>();

        when(cartDetailRepository.getCartDetailsByCartId(expectedOptionalCart)).thenReturn(cartDetailList);
        when(cartRepository.findById(id)).thenReturn(optionalCart);
        when(cartService.getCartForDetail(id)).thenReturn(expectedOptionalCart);

        //WHEN
        final var detailList = cartDetailService.getAllCartDetail(id);

        //THEN
        assertThat(detailList).isEqualTo(cartDetailList);
    }
*/

}
