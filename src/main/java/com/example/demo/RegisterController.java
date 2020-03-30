package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
