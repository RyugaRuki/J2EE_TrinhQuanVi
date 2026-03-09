package com.example.Bai6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "welcome";
    }

    @GetMapping("/home")
    public String homepage() {
        return "welcome";
    }
}
