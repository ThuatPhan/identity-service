package org.example.identityservice.repository;

import java.util.Collection;
import java.util.List;

import org.example.identityservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByName(String name);

    List<Permission> findAllByNameIn(Collection<String> name);
}
