package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddNoteController {
    @Autowired
    private NoteRepository repository;

    @PostMapping("/addNote")
    public String addNote(@RequestParam String title, @RequestParam String text, HttpServletRequest request) {
        repository.save(new Note(title, text));
        request.getSession().setAttribute("noteAdded", true);
        return "redirect:/notes";
    }
}
