package org.example.identityservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.identityservice.dto.ApiResponse;
import org.example.identityservice.dto.request.CreateUserRequest;
import org.example.identityservice.dto.response.UserResponse;
import org.example.identityservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ApiResponse.success(201, userService.createUser(request));
    }


    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(auth -> log.info(auth.getAuthority()));
        return ApiResponse.success(200, userService.getUsers());
    }

}
