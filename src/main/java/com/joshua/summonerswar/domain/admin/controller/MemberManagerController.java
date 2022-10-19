package com.joshua.summonerswar.domain.admin.controller;

import com.joshua.summonerswar.domain.admin.dto.MemberManagerDto;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.service.MemberManagerService;
import com.joshua.summonerswar.domain.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping ("/admin")
@RequiredArgsConstructor
public class MemberManagerController {

    private final MemberManagerService memberManagerService;

    private final RoleService roleService;

    /**
     * 관리자 화면 - 사용자 리스트 조회
     *
     * @param model
     * @return
     */
    @GetMapping("/accounts")
    public String getUsers (Model model) {

        List<MemberManagerDto> accounts = memberManagerService.getUsers();
        model.addAttribute("accounts", accounts);

        return "admin/user/list";
    }

    /**
     * 사용자 수정 API
     *
     * @param memberManagerDto
     * @return : 사용자 수정을 한 후에, 다시 사용자 리스트 화면으로 돌아간다.
     */
    @PostMapping("/accounts")
    public String modifyUser (MemberManagerDto memberManagerDto) {
        memberManagerService.modifyUser(memberManagerDto);
        return "redirect:/admin/accounts";
    }

    /**
     * 사용자 상세 조회 (단건 조회)
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("/accounts/{id}")
    public String getUser (@PathVariable(value = "id") Long id, Model model) {
        MemberManagerDto memberManagerDto = memberManagerService.getUser(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("account", memberManagerDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    /**
     * 사용자 삭제 후, 사용자 리스트 조회로 전환
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("/accounts/delete/{id}")
    public String removeUser (@PathVariable (value = "id") Long id, Model model) {

        memberManagerService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
