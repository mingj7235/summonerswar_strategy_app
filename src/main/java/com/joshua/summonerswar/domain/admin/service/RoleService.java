package com.joshua.summonerswar.domain.admin.service;

import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional (readOnly = true)
    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    public Role findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Transactional (readOnly = true)
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

}
