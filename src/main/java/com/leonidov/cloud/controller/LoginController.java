package com.leonidov.cloud.controller;

import com.leonidov.cloud.auth.CheckAuthenticated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final CheckAuthenticated checkAuthenticated =
            new CheckAuthenticated();

    @GetMapping
    public String loginPage() {
        if (checkAuthenticated.isAuthenticated())
            return "redirect:/user";
        return "login";
    }
}
