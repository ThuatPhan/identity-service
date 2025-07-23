package org.example.identityservice.service;

import com.nimbusds.jose.JOSEException;
import org.example.identityservice.dto.request.AuthenticationRequest;
import org.example.identityservice.dto.request.IntrospectRequest;
import org.example.identityservice.dto.request.LogoutRequest;
import org.example.identityservice.dto.request.RefreshTokenRequest;
import org.example.identityservice.dto.response.AuthenticationResponse;
import org.example.identityservice.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;

    void logout (LogoutRequest request) throws ParseException, JOSEException;
}
