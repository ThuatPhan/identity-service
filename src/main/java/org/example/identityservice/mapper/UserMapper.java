package org.example.identityservice.mapper;

import org.example.identityservice.dto.request.CreateUserRequest;
import org.example.identityservice.dto.request.UpdateUserRequest;
import org.example.identityservice.dto.response.UserResponse;
import org.example.identityservice.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User user, UpdateUserRequest request);
}
