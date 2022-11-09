package com.fsse2207.project_backend.data.CartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2207.project_backend.data.CartItem.CartItemDetailData;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;

public class AllCartItemResponseDto {
    @JsonProperty("pid")
    private Integer productId;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private Double price;
    private Integer stock;
    @JsonProperty("cart_quantity")
    private Integer cartQuantity;


    public AllCartItemResponseDto(CartItemDetailData cartItemDetailData) {
        this.productId = cartItemDetailData.getProductId();
        this.name = cartItemDetailData.getName();
        this.imageUrl = cartItemDetailData.getImageUrl();
        this.price = cartItemDetailData.getPrice();
        this.stock =  cartItemDetailData.getStock();
        this.cartQuantity = cartItemDetailData.getCartQuantity();
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

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}


