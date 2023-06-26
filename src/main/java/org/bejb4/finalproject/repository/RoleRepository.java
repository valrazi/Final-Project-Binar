package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.user.ERole;
import org.bejb4.finalproject.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
