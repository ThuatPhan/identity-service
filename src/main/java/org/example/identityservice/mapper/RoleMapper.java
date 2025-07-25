package org.example.identityservice.mapper;

import java.util.List;

import org.example.identityservice.dto.request.RoleRequest;
import org.example.identityservice.dto.response.RoleResponse;
import org.example.identityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role role);

    List<RoleResponse> toResponseList(List<Role> roles);
}
