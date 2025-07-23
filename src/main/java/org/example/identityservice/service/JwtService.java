package org.example.identityservice.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import org.example.identityservice.entity.User;

import java.text.ParseException;

public interface JwtService {
    String generateToken(User user);

    SignedJWT verifyToken(String accessToken, boolean allowExpiredToken) throws ParseException, JOSEException;

    void invalidateToken(String accessToken) throws ParseException, JOSEException;
}
