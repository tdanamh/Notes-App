package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("admin") == null) {
            return "redirect:/adminlogin";
        }
        if (request.getSession().getAttribute("successMessage") != null) {
            model.addAttribute("successMessage", request.getSession().getAttribute("successMessage"));
            request.getSession().removeAttribute("successMessage");
        }
//        Get users from database
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin";
    }
}
