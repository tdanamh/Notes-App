package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminLoginController {
    @GetMapping("/adminlogin")
    public String adminlogin(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("admin") != null) {
            return "redirect:/admin";
        }
        // If session not available, check previous login
        if (request.getSession().getAttribute("message") != null) {
            model.addAttribute("check", request.getSession().getAttribute("message"));
            request.getSession().removeAttribute("message");
        }
        return "adminlogin";
    }
}
