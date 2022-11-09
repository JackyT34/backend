package com.fsse2207.project_backend.data.CartItem;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;

public class CartItemDetailData {
    private Integer productId;
    private String name;
    private String imageUrl;
    private Double price;
    private Integer stock;
    private Integer cartQuantity;

    public CartItemDetailData(CartItemEntity entity) {
        this.productId = entity.getProduct().getProductId();
        this.name = entity.getProduct().getName();
        this.imageUrl = entity.getProduct().getImageUrl();
        this.price = entity.getProduct().getPrice();
        this.stock = entity.getProduct().getStock();
        this.cartQuantity = entity.getQuantity();
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
