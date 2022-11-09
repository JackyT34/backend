package com.fsse2207.project_backend.data.transaction;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionProductEntity;

import javax.persistence.*;

public class TransactionProductDetailData {
    private Integer tpid;

//    private Integer tid;


    private Integer productId;


    private String name;


    private String description;


    private String imageUrl;


    private Double price;


    private Integer stock;


    private Integer quantity;


    private Double subtotal;

    public TransactionProductDetailData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
//        this.tid = entity.getTransaction().getTid();
        this.productId = entity.getProductId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
        this.quantity = entity.getQuantity();
        this.subtotal = entity.getSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}