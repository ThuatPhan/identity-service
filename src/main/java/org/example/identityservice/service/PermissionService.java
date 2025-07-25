package org.example.identityservice.service;

import java.util.List;

import org.example.identityservice.dto.request.PermissionRequest;
import org.example.identityservice.dto.response.PermissionResponse;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    List<PermissionResponse> getPermissions();
}
