package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String settings(HttpServletRequest request) {
//      Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }

        return "settings";
    }
}
