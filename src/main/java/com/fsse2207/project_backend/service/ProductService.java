package com.fsse2207.project_backend.service;

import com.fsse2207.project_backend.data.product.ProductData;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;
import com.fsse2207.project_backend.exception.OutOfStockException;
import com.fsse2207.project_backend.exception.ProductNotFoundException;
import com.fsse2207.project_backend.data.product.ProductDetailData;

import java.util.ArrayList;

public interface ProductService {
    public ArrayList<ProductData> getProduct();
    public ProductDetailData getProductById(Integer pid) throws ProductNotFoundException;
    ProductEntity getProductEntityById(Integer pid) throws ProductNotFoundException;

    Integer getProductStock(Integer pid) throws ProductNotFoundException;

    boolean deductStock(Integer pid, Integer stock) throws ProductNotFoundException, OutOfStockException;
}
