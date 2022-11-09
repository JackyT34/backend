package com.fsse2207.project_backend.repository;

import com.fsse2207.project_backend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    ProductEntity findByProductId(Integer pid);
}
