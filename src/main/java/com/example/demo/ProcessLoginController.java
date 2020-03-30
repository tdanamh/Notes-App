package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessLoginController {

    // Temporary harcoded credentials until creating Database and Models
    private String emailDB = "dana@gmail.com", passDB = "1234";

    @PostMapping("/processLogin")
    public String processLogin(@RequestParam String email, @RequestParam String pass, Model model) {
        if ( !email.equals(emailDB) || ! pass.equals(passDB)){
            model.addAttribute("check", "Username or password incorrect!");
            return "index";
        }
        model.addAttribute("check", email);
        return "notes";
    }
}
