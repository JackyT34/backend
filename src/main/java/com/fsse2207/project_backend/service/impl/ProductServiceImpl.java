package com.fsse2207.project_backend.service.impl;

import com.fsse2207.project_backend.data.product.ProductData;
import com.fsse2207.project_backend.exception.OutOfStockException;
import com.fsse2207.project_backend.exception.ProductNotFoundException;
import com.fsse2207.project_backend.repository.ProductRepository;
import com.fsse2207.project_backend.data.product.ProductDetailData;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;
import com.fsse2207.project_backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ArrayList<ProductData> getProduct() {
        ArrayList<ProductData> products = new ArrayList<>();
        for (ProductEntity entity : productRepository.findAll()) {
            products.add(new ProductData(entity));
        }
        return products;
    }

    @Override
    public ProductDetailData getProductById(Integer pid) throws ProductNotFoundException {
        ProductEntity entity = getProductEntityById(pid);
        ProductDetailData productDetailData = new ProductDetailData(entity);
        return productDetailData;
    }

    @Override
    public ProductEntity getProductEntityById(Integer pid) throws ProductNotFoundException {
        ProductEntity entity = productRepository.findByProductId(pid);
        if (entity != null) {
            return entity;
        }
        logger.warn("cannot find a product by pid.");
        throw new ProductNotFoundException();
    }

    @Override
    public Integer getProductStock(Integer pid) throws ProductNotFoundException {
        return getProductEntityById(pid).getStock();
    }

    @Override
    public boolean deductStock(Integer pid, Integer amount) throws ProductNotFoundException, OutOfStockException {
        ProductEntity entity = getProductEntityById(pid);
        if (entity.getStock() - amount < 0) {
            logger.warn("Out of stock!");
            throw new OutOfStockException();
        } else {
            entity.setStock(entity.getStock() - amount);
            productRepository.save(entity);
            return true;
        }
    }

}
