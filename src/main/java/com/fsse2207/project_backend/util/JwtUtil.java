package com.fsse2207.project_backend.util;

import com.fsse2207.project_backend.data.user.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUser(JwtAuthenticationToken jwt){
        return new FirebaseUserData(jwt);
    }
}