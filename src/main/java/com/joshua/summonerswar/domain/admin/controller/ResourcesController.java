package com.joshua.summonerswar.domain.admin.controller;

import com.joshua.summonerswar.domain.admin.dto.ResourcesDto;
import com.joshua.summonerswar.domain.admin.entity.Resources;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.service.ResourcesService;
import com.joshua.summonerswar.domain.admin.service.RoleService;
import com.joshua.summonerswar.global.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping ("/admin")
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;
    private final RoleService roleService;
    private final UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    /**
     * 접근이 가능한 자원들 목록 조회
     *
     * @param model
     * @return
     */
    @GetMapping("/resources")
    public String getResources (Model model) {
        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    /**
     * 접근 자원 등록
     *
     * @param resourcesDto
     * @return
     */
    @PostMapping("/resources")
    public String createResources (ResourcesDto resourcesDto) {

        ModelMapper modelMapper = new ModelMapper();
        Role role = roleService.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);
        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/admin/resources";
    }

    /**
     * 자원 상세 조회
     *
     * @param model
     * @return
     */
    @GetMapping ("/resources/register")
    public String viewResources (Model model) {
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        ResourcesDto resourcesDto = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resourcesDto.setRoleSet(roleSet);
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping ("/resources/{id}")
    public String getResources (@PathVariable String id, Model model) {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        Resources resources = resourcesService.getResources(Long.parseLong(id));
        model.addAttribute("resources", ResourcesDto.toDtoFromEntity (resources));

        return "admin/resource/detail";
    }

    @GetMapping ("/resources/delete/{id}")
    public String removeResources (@PathVariable String id, Model model) {
        Resources resources = resourcesService.getResources(Long.parseLong(id));
        resourcesService.deleteResources(Long.parseLong(id));
        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/admin/resources";
    }
}
