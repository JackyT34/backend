package com.fsse2207.project_backend.data.user.data;

import com.fsse2207.project_backend.data.user.data.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserDetailData {

    private Integer uid;

    private String email;

    private String firebaseUid;

    public UserDetailData(UserEntity user) {
        this.uid = user.getUid();
        this.email = user.getEmail();
        this.firebaseUid = user.getFirebaseUid();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}
