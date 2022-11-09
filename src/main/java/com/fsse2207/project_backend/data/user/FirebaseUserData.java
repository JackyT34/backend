package com.fsse2207.project_backend.data.user;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class FirebaseUserData {
    private String email;
    private String firebaseUid;

    public FirebaseUserData(JwtAuthenticationToken jwt){
        this.email = (String) jwt.getTokenAttributes().get("email");
        this.firebaseUid = (String) jwt.getTokenAttributes().get("user_id");
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
