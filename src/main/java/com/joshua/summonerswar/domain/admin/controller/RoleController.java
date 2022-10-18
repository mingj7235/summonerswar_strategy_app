package com.joshua.summonerswar.domain.admin.controller;

import com.joshua.summonerswar.domain.admin.dto.RoleDto;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/admin/roles")
    public String getRoles (Model model) {

        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        return "admin/role/list";
    }

    @GetMapping ("/admin/roles/register")
    public String viewRoles (Model model) {

        RoleDto role = new RoleDto();
        model.addAttribute("role", role);

        return "admin/role/detail";
    }

    @PostMapping("/admin/roles")
    public String createRole (RoleDto roleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        roleService.createRole(role);

        return "redirect:/admin/roles";
    }

    @GetMapping ("/admin/roles/{id}")
    public String getRole (@PathVariable String id, Model model) {

        Role role = roleService.getRole(Long.parseLong(id));

        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        model.addAttribute("role", roleDto);

        return "admin/role/detail";
    }

    @GetMapping ("/admin/roles/delete/{id}")
    public String removeResources (@PathVariable String id, Model model) {

        Role role = roleService.getRole(Long.parseLong(id));
        roleService.deleteRole(Long.parseLong(id));

        return "redirect:/admin/resources";

    }
}
