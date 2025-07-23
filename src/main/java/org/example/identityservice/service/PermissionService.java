package org.example.identityservice.service;

import org.example.identityservice.dto.request.PermissionRequest;
import org.example.identityservice.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    List<PermissionResponse> getPermissions();
}
