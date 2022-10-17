package com.joshua.summonerswar.domain.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping ("/home")
    public String adminHome () {
        return "admin/home";
    }
}
