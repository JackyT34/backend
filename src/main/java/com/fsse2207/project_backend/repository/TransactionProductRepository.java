package com.fsse2207.project_backend.repository;

import com.fsse2207.project_backend.data.transaction.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
