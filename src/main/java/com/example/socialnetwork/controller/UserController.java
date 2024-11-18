package com.example.socialnetwork.controller;

import com.example.socialnetwork.core.UserCore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserCore userCore;

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userCore.findAllUsers());
        return "users";
    }

    @GetMapping("/user/get")
    public String getUserById(@RequestParam String id, Model model) {
        model.addAttribute("users", userCore.findUserById(id));
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
