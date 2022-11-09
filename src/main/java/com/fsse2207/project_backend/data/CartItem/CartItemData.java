package com.fsse2207.project_backend.data.CartItem;


import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.product.entity.ProductEntity;

public class CartItemData {
    private Integer cid;

    private ProductEntity pid;

    private Integer uid;

    private Integer quantity;



    public CartItemData(CartItemEntity cartItemEntity) {
        this.pid = cartItemEntity.getProduct();
        this.uid = cartItemEntity.getUser().getUid();
        this.quantity = cartItemEntity.getQuantity();
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductEntity getPid() {
        return pid;
    }

    public void setPid(ProductEntity pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
