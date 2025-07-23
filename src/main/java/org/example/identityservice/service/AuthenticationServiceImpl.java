package org.example.identityservice.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.identityservice.dto.request.AuthenticationRequest;
import org.example.identityservice.dto.request.IntrospectRequest;
import org.example.identityservice.dto.request.LogoutRequest;
import org.example.identityservice.dto.request.RefreshTokenRequest;
import org.example.identityservice.dto.response.AuthenticationResponse;
import org.example.identityservice.dto.response.IntrospectResponse;
import org.example.identityservice.entity.InvalidatedToken;
import org.example.identityservice.entity.User;
import org.example.identityservice.exception.AppException;
import org.example.identityservice.exception.ErrorCode;
import org.example.identityservice.repository.InvalidatedTokenRepository;
import org.example.identityservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    JwtService jwtService;
    InvalidatedTokenRepository invalidatedTokenRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        boolean isValid = true;

        try {
            jwtService.verifyToken(request.getAccessToken(), false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().isValid(isValid).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User userToAuthenticate = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), userToAuthenticate.getPassword());

        if (!isPasswordMatch) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtService.generateToken(userToAuthenticate);

        return AuthenticationResponse.builder().accessToken(accessToken).build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.verifyToken(request.getAccessToken(), true);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder().id(jwtId).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        String newAccessToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().accessToken(newAccessToken).build();
    }


    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.verifyToken(request.getAccessToken(), false);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder().id(jwtId).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);
    }
}
