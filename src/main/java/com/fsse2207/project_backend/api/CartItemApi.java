package com.fsse2207.project_backend.api;


import com.fsse2207.project_backend.config.CrossOriginConfig;
import com.fsse2207.project_backend.data.CartItem.CartItemDetailData;
import com.fsse2207.project_backend.data.CartItem.dto.AllCartItemResponseDto;
import com.fsse2207.project_backend.data.CartItem.dto.CartItemStatusResponseDto;
import com.fsse2207.project_backend.data.CartItem.dto.DeleteCartItemResponseDto;
import com.fsse2207.project_backend.data.CartItem.dto.UpdateCartQuantityResponseDto;
import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.exception.OutOfStockException;
import com.fsse2207.project_backend.exception.ProdouctExistedException;
import com.fsse2207.project_backend.exception.ProductNotFoundException;
import com.fsse2207.project_backend.exception.ProductNotInCartException;
import com.fsse2207.project_backend.service.CartItemService;
import com.fsse2207.project_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
@CrossOrigin(origins = {CrossOriginConfig.devBaseUrl, CrossOriginConfig.productionBaseUrl, CrossOriginConfig.productionS3BaseUrl })
@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public CartItemStatusResponseDto addCartItem(@PathVariable Integer pid, @PathVariable Integer quantity, JwtAuthenticationToken jwt) throws ProductNotFoundException, OutOfStockException, ProdouctExistedException {
        FirebaseUserData loggedUser = JwtUtil.getFirebaseUser(jwt);
        cartItemService.addCartItem(pid, quantity, loggedUser);
        return new CartItemStatusResponseDto();
    }

    @GetMapping("/get")
    public ArrayList<AllCartItemResponseDto> getAllCartItem(JwtAuthenticationToken jwt) {
        FirebaseUserData loggedUser = JwtUtil.getFirebaseUser(jwt);
        ArrayList<CartItemDetailData> allCartItem = cartItemService.getAllCartItem(loggedUser);
        ArrayList<AllCartItemResponseDto> allCartItemResponseDtos = new ArrayList<>();
        for (CartItemDetailData cartItemDetailData : allCartItem) {
            allCartItemResponseDtos.add(new AllCartItemResponseDto(cartItemDetailData));
        }
        return allCartItemResponseDtos;
    }

    @PatchMapping("/{pid}/{quantity}")
    public UpdateCartQuantityResponseDto updateCartQuantity(@PathVariable Integer pid, @PathVariable Integer quantity, JwtAuthenticationToken jwt) throws ProductNotFoundException, OutOfStockException, ProductNotInCartException {
        FirebaseUserData loggedUser = JwtUtil.getFirebaseUser(jwt);
        CartItemDetailData cartItemDetailData = cartItemService.updateCartQuantity(pid, quantity, loggedUser);
        return new UpdateCartQuantityResponseDto(cartItemDetailData);
    }

    @DeleteMapping("/{pid}")
    public DeleteCartItemResponseDto deleteCartItem(@PathVariable Integer pid, JwtAuthenticationToken jwt) throws ProductNotInCartException, ProductNotFoundException {
        FirebaseUserData loggedUser = JwtUtil.getFirebaseUser(jwt);
        cartItemService.deleteCartItem(pid, loggedUser);
        return new DeleteCartItemResponseDto();

    }
}
