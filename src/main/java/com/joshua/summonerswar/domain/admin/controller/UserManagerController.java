package com.joshua.summonerswar.domain.admin.controller;

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
public class UserManagerController {

    private final UserService userService;

    private final RoleService roleService;

    @GetMapping("/admin/accounts")
    public String getUsers (Model model) {

        List<Account> accounts = userService.getUsers();
        model.addAttribute("accounts", accounts);

        return "admin/user/list";
    }

    @PostMapping("/admin/accounts")
    public String modifyUser (AccountDto accountDto) {
        userService.modifyUser(accountDto);
        return "redirect:/admin/accounts";
    }

    @GetMapping ("/admin/accounts/{id}")
    public String getUser (@PathVariable(value = "id") Long id, Model model) {
        AccountDto accountDto = userService.getUser(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("account", accountDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping ("/admin/accounts/delete/{id}")
    public String removeUser (@PathVariable (value = "id") Long id, Model model) {

        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
