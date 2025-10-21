package dev.gml.herbarium_backend.role;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public RoleEntity getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Set<RoleEntity> assignDefaultRole() {
        RoleEntity defaultRole = this.getById(2L); // ROLE_USER

        Set<RoleEntity> roles = new HashSet<>();

        roles.add(defaultRole);

        return roles;
    }
}
