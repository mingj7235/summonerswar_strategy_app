package com.joshua.summonerswar.domain.admin.controller;

import com.joshua.summonerswar.domain.admin.dto.MemberManagerDto;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.service.MemberManagerService;
import com.joshua.summonerswar.domain.admin.service.RoleService;
import com.joshua.summonerswar.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberManagerController {

    private final MemberManagerService memberManagerService;

    private final RoleService roleService;

    @GetMapping("/admin/accounts")
    public String getUsers (Model model) {

        List<MemberManagerDto> accounts = memberManagerService.getUsers();
        model.addAttribute("accounts", accounts);

        return "admin/user/list";
    }

    @PostMapping("/admin/accounts")
    public String modifyUser (MemberManagerDto memberManagerDto) {
        memberManagerService.modifyUser(memberManagerDto);
        return "redirect:/admin/accounts";
    }

    @GetMapping ("/admin/accounts/{id}")
    public String getUser (@PathVariable(value = "id") Long id, Model model) {
        MemberManagerDto memberManagerDto = memberManagerService.getUser(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("account", memberManagerDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping ("/admin/accounts/delete/{id}")
    public String removeUser (@PathVariable (value = "id") Long id, Model model) {

        memberManagerService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
