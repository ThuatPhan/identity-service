package org.example.identityservice.service;

import java.util.List;

import org.example.identityservice.dto.request.PermissionRequest;
import org.example.identityservice.dto.response.PermissionResponse;
import org.example.identityservice.entity.Permission;
import org.example.identityservice.exception.AppException;
import org.example.identityservice.exception.ErrorCode;
import org.example.identityservice.mapper.PermissionMapper;
import org.example.identityservice.repository.PermissionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        boolean isPermissionExists = permissionRepository.existsByName(request.getName());
        if (isPermissionExists) {
            throw new AppException(ErrorCode.PERMISSION_ALREADY_EXISTS);
        }

        Permission newPermission = permissionMapper.toEntity(request);

        return permissionMapper.toResponse(permissionRepository.save(newPermission));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<PermissionResponse> getPermissions() {
        return permissionMapper.toResponseList(permissionRepository.findAll());
    }
}
