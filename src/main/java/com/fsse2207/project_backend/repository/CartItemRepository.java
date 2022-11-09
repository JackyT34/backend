package com.fsse2207.project_backend.repository;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {

    CartItemEntity findByProductAndUser(ProductEntity product, UserEntity user);

    ArrayList<CartItemEntity> findByUser(UserEntity userEntity);

}
