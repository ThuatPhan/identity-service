package org.example.identityservice.mapper;

import org.example.identityservice.dto.request.PermissionRequest;
import org.example.identityservice.dto.response.PermissionResponse;
import org.example.identityservice.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper {
    Permission toEntity(PermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    List<PermissionResponse> toResponseList(List<Permission> permissions);
}
