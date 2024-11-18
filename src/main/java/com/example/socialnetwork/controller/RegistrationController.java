package com.example.socialnetwork.controller;

import com.example.socialnetwork.core.UserCore;
import com.example.socialnetwork.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserCore userCore;

    @GetMapping("/user/register")
    public String registration(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "registration";
    }

    @PostMapping("/user/register")
    public String registration(@ModelAttribute("userDto") UserDTO userDto) {
        userCore.createUser(userDto);
        return "redirect:/login";
    }
}
