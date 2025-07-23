package org.example.identityservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.identityservice.dto.request.CreateUserRequest;
import org.example.identityservice.dto.response.UserResponse;
import org.example.identityservice.entity.User;
import org.example.identityservice.mapper.UserMapper;
import org.example.identityservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User newUser = userMapper.toEntity(request);

        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        User savedUser = userRepository.save(newUser);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }
}
