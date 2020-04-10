package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminDeleteNoteController {
    @Autowired
    private NoteRepository repository;

    @GetMapping("/adminDeleteNote")
    public String adminDeleteNote(@RequestParam String id, HttpServletRequest request) {
//        Secure
        if (request.getSession().getAttribute("admin") == null) {
            return "redirect:/adminlogin";
        }
        repository.deleteById(id);
        request.getSession().setAttribute("noteDeleted", "The note was deleted successfully.");
        return "redirect:/manageNotes";
    }
}
