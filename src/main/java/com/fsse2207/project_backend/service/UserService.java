package com.fsse2207.project_backend.service;

import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;

public interface UserService {
    public UserEntity getUSerEntityByFirebaseUserData(FirebaseUserData userData);
}
