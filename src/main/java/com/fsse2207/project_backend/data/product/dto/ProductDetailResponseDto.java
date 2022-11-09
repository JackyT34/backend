package com.fsse2207.project_backend.data.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2207.project_backend.data.product.ProductDetailData;
import com.fsse2207.project_backend.data.transaction.TransactionProductDetailData;

public class ProductDetailResponseDto {
    @JsonProperty("pid")
    private Integer productId;

    private String name;

    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    private Double price;

    private Integer stock;

    public ProductDetailResponseDto(ProductDetailData productDetailData) {
        this.productId = productDetailData.getProductId();
        this.name = productDetailData.getName();
        this.description = productDetailData.getDescription();
        this.imageUrl = productDetailData.getImageUrl();
        this.price = productDetailData.getPrice();
        this.stock = productDetailData.getStock();
    }

    public ProductDetailResponseDto(TransactionProductDetailData data) {
        this.productId = data.getProductId();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
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

