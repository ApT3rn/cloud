package com.leonidov.cloud.controller;

import com.leonidov.cloud.auth.Mediator;
import com.leonidov.cloud.model.File;
import com.leonidov.cloud.model.User;
import com.leonidov.cloud.service.FileService;
import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final FileService fileService;
    private final UserService userService;

    @Autowired
    private UserController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    private User getUser() {
        return Mediator.getUser();
    }

    private String getUserId() {
        return getUser().getId().toString();
    }

    @GetMapping
    public String userPage(Model model) {
        List<File> allFiles = fileService.allFiles(getUserId(), "");
        model.addAttribute("user", getUser());
        model.addAttribute("allFiles", allFiles);
        return "user";
    }

    @GetMapping("settings")
    public String settingsPage(Model model) {
        model.addAttribute("user", getUser());
        return "settings";
    }

    @PostMapping("settings")
    public String settingsPage(User user) {
        userService.saveAndFlush(user);
        return "redirect:/user/settings";
    }

    @GetMapping("{path}")
    public String inDirectory(@PathVariable("path") String path, Model model) {
        List<File> allFiles = fileService.allFiles(getUserId(), path);
        model.addAttribute("user", getUser());
        model.addAttribute("allFiles", allFiles);
        return "user";
    }
}
