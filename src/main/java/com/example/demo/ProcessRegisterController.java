package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessRegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShortcutRepository shortcutRepository;

    @PostMapping("/processRegister")
    public String processRegister(@RequestParam String email, @RequestParam String pass, @RequestParam String cpass, Model model) {
        if (!validatePasswords(pass, cpass)) {
            model.addAttribute("check","Your passwords don't match!");
            return "register";
        } else {
            if (userRepository.findByEmail(email) == null) { // If the email is not already in database
                User user = new User(email, pass);
                userRepository.save(user);
                String userId = userRepository.findByEmail(email).id;
                shortcutRepository.save(new Shortcut(userId, true, true));
                model.addAttribute("check", "Account created successfully!");
            }
            else {
                model.addAttribute("check", "User already exists!");
                return "register";
            }
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
