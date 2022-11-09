package com.fsse2207.project_backend.service.impl;

import com.fsse2207.project_backend.data.CartItem.CartItemData;
import com.fsse2207.project_backend.data.CartItem.CartItemDetailData;
import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;
import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import com.fsse2207.project_backend.exception.CartItemNotFoundException;
import com.fsse2207.project_backend.exception.OutOfStockException;
import com.fsse2207.project_backend.exception.ProductNotFoundException;
import com.fsse2207.project_backend.exception.ProductNotInCartException;
import com.fsse2207.project_backend.repository.CartItemRepository;
import com.fsse2207.project_backend.service.CartItemService;
import com.fsse2207.project_backend.service.ProductService;
import com.fsse2207.project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private UserService userService;
    private ProductService productService;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItemData addCartItem(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws ProductNotFoundException, OutOfStockException {
        ProductEntity entity = productService.getProductEntityById(pid);
        UserEntity userEntity = userService.getUSerEntityByFirebaseUserData(firebaseUserData);
        if (entity.getStock() <= 0 || quantity > entity.getStock()) {
            logger.warn("Out of stock!");
            throw new OutOfStockException();
        }
        CartItemEntity foundCartEntity = cartItemRepository.findByProductAndUser(entity, userEntity);
        if (foundCartEntity != null) {
            if (quantity + foundCartEntity.getQuantity() > entity.getStock()) {
                logger.warn("Out of stock!");
                throw new OutOfStockException();
            }
            foundCartEntity.setQuantity(foundCartEntity.getQuantity() + quantity);
            cartItemRepository.save(foundCartEntity);
            CartItemData cartItemData = new CartItemData(foundCartEntity);
            return cartItemData;

        } else {
            CartItemEntity cartItemEntity = new CartItemEntity(entity, quantity, userEntity);
            cartItemEntity = cartItemRepository.save(cartItemEntity);
            CartItemData cartItemData = new CartItemData(cartItemEntity);
            return cartItemData;
        }
    }

    @Override
    public ArrayList<CartItemDetailData> getAllCartItem(FirebaseUserData firebaseUserData) {
        userService.getUSerEntityByFirebaseUserData(firebaseUserData);
        ArrayList<CartItemDetailData> allCartItem = new ArrayList<>();
        for (CartItemEntity entity : cartItemRepository.findAll()) {
            allCartItem.add(new CartItemDetailData(entity));
        }
        return allCartItem;
    }


    @Override
    public CartItemDetailData updateCartQuantity(Integer pid, Integer quantity, FirebaseUserData firebaseUserData) throws ProductNotFoundException, ProductNotInCartException, OutOfStockException {
        ProductEntity productEntity = productService.getProductEntityById(pid);
        UserEntity userEntity = userService.getUSerEntityByFirebaseUserData(firebaseUserData);
        CartItemEntity foundCartEntity = cartItemRepository.findByProductAndUser(productEntity, userEntity);
        if (foundCartEntity != null) {
            if (quantity > foundCartEntity.getProduct().getStock()) {
                logger.warn("not enough stock.");
                throw new OutOfStockException();
            }
            foundCartEntity.setQuantity(quantity);
            cartItemRepository.save(foundCartEntity);
            return new CartItemDetailData(foundCartEntity);
        }
        logger.warn("product doesn't in cart.");
        throw new ProductNotInCartException();
    }

    @Override
    public CartItemDetailData deleteCartItem(Integer pid, FirebaseUserData firebaseUserData) throws ProductNotFoundException, ProductNotInCartException {
        ProductEntity productEntity = productService.getProductEntityById(pid);
        UserEntity userEntity = userService.getUSerEntityByFirebaseUserData(firebaseUserData);
        CartItemEntity foundCartEntity = cartItemRepository.findByProductAndUser(productEntity, userEntity);
        if (foundCartEntity != null) {
            CartItemDetailData cartItemDetailData = new CartItemDetailData(foundCartEntity);
            cartItemRepository.delete(foundCartEntity);
            return cartItemDetailData;
        }
        logger.warn("product doesn't in cart.");
        throw new ProductNotInCartException();
    }

    @Override
    public boolean emptyCartItem(UserEntity user) throws CartItemNotFoundException {
        List<CartItemEntity> itemEntities = cartItemRepository.findByUser(user);
        if (itemEntities == null) {
            logger.warn("Cart is empty!");
            throw new CartItemNotFoundException();
        } else {
            cartItemRepository.deleteAll(itemEntities);
            return true;
        }
    }

}

