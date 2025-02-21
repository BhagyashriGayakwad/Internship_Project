package com.flatmate.resolver.model.role;

import com.flatmate.resolver.model.Role;
import com.flatmate.resolver.model.RoleType;
import com.flatmate.resolver.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class DatabaseSeeder {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        createRoleIfNotExists(RoleType.ROLE_USER);
        createRoleIfNotExists(RoleType.ROLE_ADMIN);
    }

    private void createRoleIfNotExists(RoleType roleType) {
        Optional<Role> role = roleRepository.findByName(roleType);
        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(roleType);
            roleRepository.save(newRole);
            System.out.println("Created Role: " + roleType);
        }
    }
}
