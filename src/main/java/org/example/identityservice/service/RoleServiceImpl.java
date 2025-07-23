package org.example.identityservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.identityservice.dto.request.RoleRequest;
import org.example.identityservice.dto.response.RoleResponse;
import org.example.identityservice.entity.Permission;
import org.example.identityservice.entity.Role;
import org.example.identityservice.exception.AppException;
import org.example.identityservice.exception.ErrorCode;
import org.example.identityservice.mapper.RoleMapper;
import org.example.identityservice.repository.PermissionRepository;
import org.example.identityservice.repository.RoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    PermissionRepository permissionRepository;
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RoleResponse createRole(RoleRequest request) {
        boolean isRoleExists = roleRepository.existsByName(request.getName());
        if (isRoleExists) {
            throw new AppException(ErrorCode.ROLE_ALREADY_EXISTS);
        }

        Role newRole = roleMapper.toEntity(request);

        List<Permission> permissions = permissionRepository.findAllByNameIn(request.getPermissions());
        newRole.setPermissions(new HashSet<>(permissions));

        return roleMapper.toResponse(roleRepository.save(newRole));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<RoleResponse> getRoles() {
        return roleMapper.toResponseList(roleRepository.findAll());
    }
}
