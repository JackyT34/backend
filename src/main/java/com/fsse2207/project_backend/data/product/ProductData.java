package com.fsse2207.project_backend.data.product;

import com.fsse2207.project_backend.data.product.entity.ProductEntity;

public class ProductData {

    private Integer productId;

    private String name;

    private String imageUrl;

    private Double price;

    private Integer stock;

    private Boolean stockStatus;

    public ProductData(ProductEntity entity) {
        this.productId = entity.getProductId();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
        setStockStatus(entity.getStock());
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

    public Boolean getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stock) {
        if(stock <= 0){
            stockStatus = false;
        } else {
            stockStatus = true;
        }
    }
}
