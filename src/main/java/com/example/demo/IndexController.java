package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping({"/index", "/"})
    public String index(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null){
            return "redirect:/notes";
        }
        return "index";
    }
}
