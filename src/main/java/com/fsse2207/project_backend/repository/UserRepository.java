package com.fsse2207.project_backend.repository;

import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByfirebaseUid(String firebaseUid);
}
