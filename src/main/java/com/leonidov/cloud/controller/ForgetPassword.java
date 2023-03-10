package com.leonidov.cloud.controller;

import com.leonidov.cloud.auth.CheckAuthenticated;
import com.leonidov.cloud.model.User;
import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/forget")
public class ForgetPassword {

    private final UserService userService;
    private final CheckAuthenticated checkAuthenticated;

    @Autowired
    private ForgetPassword(UserService userService) {
        this.userService = userService;
        this.checkAuthenticated = new CheckAuthenticated();
    }

    @GetMapping
    public String forgetPage() {
        if (checkAuthenticated.isAuthenticated())
            return "redirect:/user";
        return "forget";
    }

    @PostMapping
    public String forgetPassword(@RequestParam String email) {
        Optional<User> userFromDb = userService.findUserByEmail(email);
        if (userFromDb.isPresent()) {
            System.out.println("Ваш отправлен на почту!");
        } else {
            System.out.println("Данный электронная почта не зарегистрирована!");
        }
        return "forget";
    }
}

