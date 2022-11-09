package com.fsse2207.project_backend.service;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionProductEntity;
import com.fsse2207.project_backend.exception.CartItemNotFoundException;

public interface TransactionProductService {
    TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity) throws CartItemNotFoundException;
}
