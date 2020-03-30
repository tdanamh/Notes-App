package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessRegisterController {
    @PostMapping("/processRegister")
    public String processRegister(@RequestParam String pass, @RequestParam String cpass, Model model) {
        if (!validatePasswords(pass, cpass)) {
            model.addAttribute("check","Your passwords don't match!");
            return "register";
        } else {
            model.addAttribute("check", "Account created successfully!");
        }
        return "index";
    }

    public Boolean validatePasswords(String pass, String cpass) {
        if (pass.equals(cpass)) {
            return true;
        }
        return false;
    }
}
