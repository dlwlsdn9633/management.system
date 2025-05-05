package com.service.management.system.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "pages/index";
    }
    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }
    @GetMapping("/register")
    public String register() {
        return "pages/register";
    }
}
