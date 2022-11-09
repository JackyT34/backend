package com.fsse2207.project_backend.data.transaction.entity;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.transaction.Status;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "buyerUid", nullable = false)
    private UserEntity user;

    @Column(name = "createdTime", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "total", nullable = false)
    private Double total;


//mappedBy : when transactionEntity is requested by transactionRepository, TransactionProductEntity would be counted into transactionEntity.
    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProductEntities = new ArrayList<TransactionProductEntity>();

    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity userEntity, ArrayList<CartItemEntity> cartItemEntities){
        this.user = userEntity;
        this.createdTime = LocalDateTime.now();
        this.status = Status.PREPARE;
        setTotal(cartItemEntities);
    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTine) {
        this.createdTime = createdTine;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setTotal(ArrayList<CartItemEntity> items){
        this.total = 0D;
        for(CartItemEntity item : items){
            total += item.getProduct().getPrice() * item.getQuantity();
        }
    }

    public List<TransactionProductEntity> getTransactionProductEntities() {
        return transactionProductEntities;
    }

    public void setTransactionProductEntities(ArrayList<TransactionProductEntity> transactionProductEntities) {
        this.transactionProductEntities = transactionProductEntities;
    }
}
