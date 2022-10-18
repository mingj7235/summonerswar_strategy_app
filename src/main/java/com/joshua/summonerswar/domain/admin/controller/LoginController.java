package com.joshua.summonerswar.domain.admin.controller;

import com.joshua.summonerswar.domain.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Objects;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/")
    public String homeToLogin (Model model) {
        return "home";
    }

    @GetMapping ("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam (value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "member/login";
    }

    @GetMapping ("/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login";
    }

    @GetMapping ("/denied")
    public String accessDenied (@RequestParam (value = "exception", required = false) String exception,
                                Principal principal,
                                Model model) {

        Member member = null;

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            member = (Member) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        }

        model.addAttribute("email", Objects.requireNonNull(member).getEmail());
        model.addAttribute("exception", exception);

        return "errors/denied";
    }
}
