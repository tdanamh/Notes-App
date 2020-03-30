package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotesController {
    @GetMapping("/notes")
    public String notes(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            model.addAttribute("check", request.getSession().getAttribute("user"));
            return "notes";
        }
        return "redirect:/index";
    }
}
