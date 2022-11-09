package com.fsse2207.project_backend.repository;

import com.fsse2207.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    TransactionEntity findByUser(UserEntity user);
}
