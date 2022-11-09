package com.fsse2207.project_backend.data.CartItem.entity;

import com.fsse2207.project_backend.data.product.entity.ProductEntity;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "CartItem")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public CartItemEntity() {
    }

    public CartItemEntity(ProductEntity entity, Integer quantity, UserEntity userEntity) {
        this.product = entity;
        this.quantity = quantity;
        this.user = userEntity;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity pid) {
        this.product = pid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity uid) {
        this.user = uid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
