package org.example.identityservice.service;

import java.util.List;

import org.example.identityservice.dto.request.CreatePasswordRequest;
import org.example.identityservice.dto.request.CreateUserRequest;
import org.example.identityservice.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    void createPassword(CreatePasswordRequest request);

    UserResponse getMyInfo();

    List<UserResponse> getUsers();
}
