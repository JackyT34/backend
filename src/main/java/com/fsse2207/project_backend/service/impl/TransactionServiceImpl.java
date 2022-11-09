package com.fsse2207.project_backend.service.impl;

import com.fsse2207.project_backend.data.CartItem.entity.CartItemEntity;
import com.fsse2207.project_backend.data.transaction.Status;
import com.fsse2207.project_backend.data.transaction.TransactionDetailData;
import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2207.project_backend.data.transaction.entity.TransactionProductEntity;
import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import com.fsse2207.project_backend.exception.*;
import com.fsse2207.project_backend.repository.CartItemRepository;
import com.fsse2207.project_backend.repository.TransactionRepository;
import com.fsse2207.project_backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionServiceImpl implements TransactionService {
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private UserService userService;
    private ProductService productService;
    private CartItemService cartItemService;
    private TransactionProductService transactionProductService;
    private CartItemRepository cartItemRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(UserService userService, ProductService productService, CartItemService cartItemService, TransactionProductService transactionProductService, CartItemRepository cartItemRepository, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.cartItemRepository = cartItemRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDetailData createTransaction(FirebaseUserData firebaseUserData) throws CartItemNotFoundException, TransactionExistedException {
        UserEntity user = userService.getUSerEntityByFirebaseUserData(firebaseUserData);
        ArrayList<CartItemEntity> cartItemEntities = cartItemRepository.findByUser(user);

        if (cartItemEntities == null || cartItemEntities.size() == 0) {
            logger.warn("Empty cart");
            throw new CartItemNotFoundException();
        }

//        TransactionEntity foundTransactionEntity = transactionRepository.findByUser(user);
//        if (foundTransactionEntity != null) {
//            logger.warn("Transaction exist already");
//            throw new TransactionExistedException();
//        } else {
            TransactionEntity transactionEntities = new TransactionEntity(user, cartItemEntities);
            transactionRepository.save(transactionEntities);

            ArrayList<TransactionProductEntity> transactionProductEntities = new ArrayList<>();
            for (CartItemEntity item : cartItemEntities) {
                transactionProductEntities.add(transactionProductService.createTransactionProduct(transactionEntities, item));
            }

            transactionEntities.setTransactionProductEntities(transactionProductEntities);
        System.out.println();
            return new TransactionDetailData(transactionEntities);
//        }

    }

    @Override
    public TransactionDetailData getTransactionByTid(Integer tid) throws TransactionNotFoundException {
        TransactionEntity transactionEntity = getTransactionEntityByTid(tid);
        TransactionDetailData transactionDetailData = new TransactionDetailData(transactionEntity);
        return transactionDetailData;
    }

    @Override
    public TransactionDetailData payTransaction(Integer tid) throws TransactionNotFoundException, OutOfStockException, ProductNotFoundException, TransactionStatusException {
        TransactionEntity transactionEntity = getTransactionEntityByTid(tid);

        if (transactionEntity.getStatus() != Status.PREPARE) {
            logger.warn("The transaction status is invalid");
            throw new TransactionStatusException();
        }
        checkStock(transactionEntity);

        for (TransactionProductEntity tpEntity : transactionEntity.getTransactionProductEntities()) {
            productService.deductStock(tpEntity.getProductId(), tpEntity.getQuantity());
        }

        transactionEntity.setStatus(Status.PROCESSING);
        transactionRepository.save(transactionEntity);
        return new TransactionDetailData(transactionEntity);
    }

    @Override
    public TransactionDetailData successTransaction(Integer tid) throws TransactionStatusException, TransactionNotFoundException, CartItemNotFoundException {
        TransactionEntity transactionEntity = getTransactionEntityByTid(tid);

        if (transactionEntity.getStatus() != Status.PROCESSING) {
            logger.warn("The transaction status is invalid");
            throw new TransactionStatusException();
        }

        cartItemService.emptyCartItem(transactionEntity.getUser());
        transactionEntity.setStatus(Status.SUCCESS);
        transactionRepository.save(transactionEntity);
        return new TransactionDetailData(transactionEntity);
    }


    public TransactionEntity getTransactionEntityByTid(Integer tid) throws TransactionNotFoundException {
        TransactionEntity transactionEntity = transactionRepository.findById(tid).orElse(null);
        if (transactionEntity == null) {
            logger.warn("Transaction not found");
            throw new TransactionNotFoundException();
        } else {
            return transactionEntity;
        }
    }

    public boolean checkStock(TransactionEntity entity) throws ProductNotFoundException, OutOfStockException {
        for (TransactionProductEntity tpEntity : entity.getTransactionProductEntities()) {
            if (tpEntity.getQuantity() > productService.getProductStock(tpEntity.getProductId())) {
                logger.warn("Out of stock!");
                throw new OutOfStockException();
            }
        }
        return true;
    }
}
