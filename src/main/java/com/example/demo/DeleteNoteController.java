package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteNoteController {
    @Autowired
    private NoteRepository repository;

    @PostMapping("/deleteNote")
    public String deleteNote(@RequestParam String id, HttpServletRequest request) {
//        Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        repository.deleteById(id);
        request.getSession().setAttribute("noteDeleted", true);
        return "redirect:/notes";
    }
}
