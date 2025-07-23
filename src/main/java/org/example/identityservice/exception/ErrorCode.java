package org.example.identityservice.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    REVOKED_TOKEN(1001, HttpStatus.UNAUTHORIZED, "Token has been revoked"),
    INVALID_TOKEN(1002, HttpStatus.UNAUTHORIZED, "Invalid token signature"),
    EXPIRED_TOKEN(1003, HttpStatus.UNAUTHORIZED, "Token has expired"),

    USERNAME_ALREADY_EXISTS(1004, HttpStatus.UNAUTHORIZED, "Username already exists"),
    USER_NOT_FOUND(1005, HttpStatus.NOT_FOUND, "User not found"),

    INVALID_CREDENTIALS(1006, HttpStatus.UNAUTHORIZED, "Invalid username or password"),
    UNAUTHENTICATED(1007, HttpStatus.UNAUTHORIZED, "Unauthenticated request"),

    UNCATEGORIZED_ERROR(5000, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"),
    ;

    int code;
    HttpStatus statusCode;
    String message;
}
