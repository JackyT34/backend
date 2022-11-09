package com.fsse2207.project_backend.service.impl;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionProductEntity;
import com.fsse2207.project_backend.exception.CartItemNotFoundException;
import com.fsse2207.project_backend.repository.TransactionProductRepository;
import com.fsse2207.project_backend.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private TransactionProductRepository transactionProductRepository;

    @Autowired
    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity) throws CartItemNotFoundException {
        if (transactionEntity == null || cartItemEntity == null) {
            throw new CartItemNotFoundException();
        }

        TransactionProductEntity transactionProductEntity = new TransactionProductEntity(transactionEntity, cartItemEntity);
        transactionProductRepository.save(transactionProductEntity);
        return transactionProductEntity;
    }
}
