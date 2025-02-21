package com.flatmate.resolver.repository;

import com.flatmate.resolver.model.Role;
import com.flatmate.resolver.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
