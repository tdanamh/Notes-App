package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminLogoutController {

    @GetMapping("/adminlogout")
    public String adminlogout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "redirect:/adminlogin";
    }
}
