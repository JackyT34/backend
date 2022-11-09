package com.fsse2207.project_backend.data.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2207.project_backend.data.product.ProductData;

public class ProductResponseDto {

    @JsonProperty("pid")
    private Integer productId;

    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    private Double price;

    @JsonProperty("has_stock")
    private Boolean stock = false;

    public ProductResponseDto(ProductData productData) {
        this.productId = productData.getProductId();
        this.name = productData.getName();
        this.imageUrl = productData.getImageUrl();
        this.price = productData.getPrice();
        this.stock = productData.getStockStatus();
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

}
