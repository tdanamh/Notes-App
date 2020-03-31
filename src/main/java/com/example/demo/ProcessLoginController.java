package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProcessLoginController {

    @Autowired
    private UserRepository repository;

    private String emailDB, passDB;

    @PostMapping("/processLogin")
    public String processLogin(@RequestParam String email, @RequestParam String pass, Model model, HttpServletRequest request) {
        User userFound = repository.findByEmail(email);
        if (userFound != null) {
            emailDB = userFound.email;
            passDB = userFound.password;

            if (email.equals(emailDB) && pass.equals(passDB)){
                request.getSession().setAttribute("user", email);
                return "redirect:/notes";
            }
        }
        model.addAttribute("check", "Username or password incorrect!");
        return "index";
    }
}
