package com.fsse2207.project_backend.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2207.project_backend.data.product.dto.ProductDetailResponseDto;
import com.fsse2207.project_backend.data.transaction.TransactionProductDetailData;
import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;

public class TransactionProductResponseDto {
    @JsonProperty("tpid")
    private Integer tpid;

    @JsonProperty("product")
    private ProductDetailResponseDto product;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("subtotal")
    private Double subtotal;

    public TransactionProductResponseDto(TransactionProductDetailData data) {
        this.tpid = data.getTpid();
        this.product = new ProductDetailResponseDto(data);
        this.quantity = data.getQuantity();
        this.subtotal = data.getSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductDetailResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductDetailResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
