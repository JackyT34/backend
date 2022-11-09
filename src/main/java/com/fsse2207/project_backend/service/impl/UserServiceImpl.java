package com.fsse2207.project_backend.service.impl;

import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.data.user.data.entity.UserEntity;
import com.fsse2207.project_backend.repository.UserRepository;
import com.fsse2207.project_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUSerEntityByFirebaseUserData(FirebaseUserData userData){
        UserEntity userEntity = userRepository.findByfirebaseUid(userData.getFirebaseUid());
        if(userEntity == null){
            UserEntity newUserEntity = new UserEntity(userData);
            userEntity = userRepository.save(newUserEntity);
        }
        return userEntity;
    }
}
