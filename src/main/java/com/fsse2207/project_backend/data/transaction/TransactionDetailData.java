package com.fsse2207.project_backend.data.transaction;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionProductEntity;
import com.fsse2207.project_backend.data.user.data.UserDetailData;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailData {

    private Integer tid;

    private UserDetailData user;

    private LocalDateTime createdTime;

    private Status status;

    private Double total;

    private List<TransactionProductDetailData> transactionProductDetailData = new ArrayList<>();

    public TransactionDetailData(TransactionEntity transactionEntity){
        this.tid = transactionEntity.getTid();
        this.user = new UserDetailData(transactionEntity.getUser());
        this.createdTime = transactionEntity.getCreatedTime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
        setTransactionProductDetailData(transactionEntity.getTransactionProductEntities());
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserDetailData getUser() {
        return user;
    }

    public void setUser(UserDetailData user) {
        this.user = user;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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

    public List<TransactionProductDetailData> getTransactionProductDetailData() {
        return transactionProductDetailData;
    }

    public void setTransactionProductDetailData(List<TransactionProductEntity> transactionProductEntities) {
        for(TransactionProductEntity transactionProductEntity : transactionProductEntities){
            this.transactionProductDetailData.add(new TransactionProductDetailData(transactionProductEntity));
        }
    }
}
