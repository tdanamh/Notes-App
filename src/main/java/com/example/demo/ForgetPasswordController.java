package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgetPasswordController {
    @GetMapping("/forgetPassword")
    public String forgetPassword(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("message") != null) {
            model.addAttribute("check", request.getSession().getAttribute("message"));
            request.getSession().setAttribute("message", null);
        }
        return "forgetpassword";
    }
}
