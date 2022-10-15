package com.joshua.summonerswar.domain.role.repository;

import com.joshua.summonerswar.domain.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName (String name);

    @Override
    void delete(Role role);

}
