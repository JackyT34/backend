package com.fsse2207.project_backend.data.user.data.entity;


import com.fsse2207.project_backend.data.user.FirebaseUserData;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "firebase_id", nullable = false)
    private String firebaseUid;

    public UserEntity() {
    }

    public UserEntity(FirebaseUserData data){
        this.email = data.getEmail();
        this.firebaseUid = data.getFirebaseUid();
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
