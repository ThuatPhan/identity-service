package org.example.identityservice.service;

import org.example.identityservice.dto.request.RoleRequest;
import org.example.identityservice.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getRoles();
}
