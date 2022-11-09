package com.fsse2207.project_backend.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2207.project_backend.data.transaction.Status;
import com.fsse2207.project_backend.data.transaction.TransactionDetailData;
import com.fsse2207.project_backend.data.transaction.TransactionProductDetailData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    @JsonProperty("tid")
    private Integer tid;

    @JsonProperty("buyer_uid")
    private Integer buyerUid;

    @JsonProperty("createdTime")
    private LocalDateTime createdTime = LocalDateTime.now();

    @JsonProperty("status")
    private Status status;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("items")
    private List<TransactionProductResponseDto> transactionProductResponseDtos = new ArrayList<>();

   public TransactionResponseDto(TransactionDetailData data){
       this.tid = data.getTid();
       this.buyerUid = data.getUser().getUid();
       this.createdTime = data.getCreatedTime();
       this.status = data.getStatus();
       this.total = data.getTotal();
       setTransactionProductResponseDtos(data);
   }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
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

    public List<TransactionProductResponseDto> getTransactionProductResponseDtos() {
        return transactionProductResponseDtos;
    }

    public void setTransactionProductResponseDtos(ArrayList<TransactionProductResponseDto> transactionProductResponseDtos) {
        this.transactionProductResponseDtos = transactionProductResponseDtos;
    }

    public void setTransactionProductResponseDtos(TransactionDetailData data){
       for(TransactionProductDetailData tpData : data.getTransactionProductDetailData()){
           this.transactionProductResponseDtos.add(new TransactionProductResponseDto(tpData));
       }
    }
}
