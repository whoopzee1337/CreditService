package com.example.mtsfinalprojectsecurity.controller;

import com.example.mtsfinalprojectsecurity.dto.UserDto;
import com.example.mtsfinalprojectsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("login")
    public String showLoginPage() {
        return "loginPage";
    }

    @GetMapping("registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "registrationPage";
    }

    @PostMapping("registration")
    public String registrateUser(@ModelAttribute("user") UserDto user) {
        userService.saveUser(user);
        return "loginPage";
    }
}
