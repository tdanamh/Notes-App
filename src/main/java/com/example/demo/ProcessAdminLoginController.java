package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProcessAdminLoginController {

    private String user = "admin@local.com";
    private String password = "admin";

    @PostMapping("/processAdminLogin")
    public String processAdminLogin(@RequestParam String email, @RequestParam String pass,  HttpServletRequest request, Model model) {
        if (!user.equals(email) || !password.equals(pass)) {
            request.getSession().setAttribute("message", "Incorrect credentials!");
            return "redirect:/adminlogin";
        }
        request.getSession().setAttribute("admin", user);
        return "redirect:/admin";
    }
}
