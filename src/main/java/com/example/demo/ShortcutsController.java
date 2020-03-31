package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShortcutsController {
    boolean nToNotes = false;
    boolean pToProfile = false;

    @GetMapping("/shortcuts")
    public String shortcuts(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            model.addAttribute("nToNotes", nToNotes);
            model.addAttribute("pToProfile", pToProfile);
            return "shortcuts";
        }
        return "redirect:/index";
    }
}
