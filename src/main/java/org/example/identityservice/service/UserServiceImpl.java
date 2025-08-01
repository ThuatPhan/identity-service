package org.example.identityservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.example.identityservice.constant.PredefinedRole;
import org.example.identityservice.dto.request.CreatePasswordRequest;
import org.example.identityservice.dto.request.CreateUserRequest;
import org.example.identityservice.dto.response.UserResponse;
import org.example.identityservice.entity.Role;
import org.example.identityservice.entity.User;
import org.example.identityservice.exception.AppException;
import org.example.identityservice.exception.ErrorCode;
import org.example.identityservice.helpers.AvatarHelper;
import org.example.identityservice.mapper.UserMapper;
import org.example.identityservice.repository.RoleRepository;
import org.example.identityservice.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User newUser = userMapper.toEntity(request);

        boolean isUserNameExists = userRepository.existsByUsername(request.getUsername());
        if (isUserNameExists) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        Set<Role> roles = new HashSet<>();
        roleRepository.findByName(PredefinedRole.USER).ifPresent(roles::add);

        newUser.setRoles(roles);
        newUser.setAvatar(AvatarHelper.generateDefaultAvatar(request.getFirstName(), request.getLastName()));

        User savedUser = userRepository.save(newUser);

        return userMapper.toResponse(savedUser);
    }

    @PreAuthorize("hasRole('USER')")
    @Override
    public void createPassword(CreatePasswordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        if (StringUtils.hasText(user.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Override
    public UserResponse getMyInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        UserResponse userResponse = userMapper.toResponse(user);
        userResponse.setHasPassword(StringUtils.hasText(user.getPassword()));

        return userResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserResponse> getUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }
}
