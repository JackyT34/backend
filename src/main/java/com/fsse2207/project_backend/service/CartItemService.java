package com.fsse2207.project_backend.service;

import com.fsse2207.project_backend.data.CartItem.CartItemData;
import com.fsse2207.project_backend.data.CartItem.CartItemDetailData;
import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import com.fsse2207.project_backend.exception.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.ArrayList;

public interface CartItemService {
    public CartItemData addCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws ProductNotFoundException, OutOfStockException, ProdouctExistedException;
    public ArrayList<CartItemDetailData> getAllCartItem(FirebaseUserData firebaseUserData);
    public CartItemDetailData updateCartQuantity(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws ProductNotFoundException, OutOfStockException, ProductNotInCartException;
    public CartItemDetailData deleteCartItem(Integer pid, FirebaseUserData firebaseUserData) throws ProductNotFoundException, ProductNotInCartException;

    boolean emptyCartItem(UserEntity user) throws CartItemNotFoundException;
}
