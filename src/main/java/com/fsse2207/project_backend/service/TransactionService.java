package com.fsse2207.project_backend.service;

import com.fsse2207.project_backend.data.transaction.TransactionDetailData;
import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.exception.*;

public interface TransactionService {
    public TransactionDetailData createTransaction(FirebaseUserData firebaseUserData) throws CartItemNotFoundException, TransactionExistedException;

    TransactionDetailData getTransactionByTid(Integer tid) throws TransactionNotFoundException;

    TransactionDetailData payTransaction(Integer tid) throws TransactionNotFoundException, OutOfStockException, ProductNotFoundException, TransactionStatusException;

    TransactionDetailData successTransaction(Integer tid) throws TransactionStatusException, TransactionNotFoundException, CartItemNotFoundException;
}
