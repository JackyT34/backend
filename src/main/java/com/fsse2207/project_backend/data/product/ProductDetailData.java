package com.fsse2207.project_backend.data.product;

import com.fsse2207.project_backend.data.product.entity.ProductEntity;

public class ProductDetailData {

    private Integer productId;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;

    private Integer stock;

    public ProductDetailData() {
    }

    public ProductDetailData(ProductEntity entity) {
            this.productId = entity.getProductId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.imageUrl = entity.getImageUrl();
            this.price = entity.getPrice();
            this.stock = entity.getStock();
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
}
