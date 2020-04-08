package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MassDeleteNotesController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/massDeleteNotes")
    public String banUser(@RequestParam String id, HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("admin") == null) {
            return "redirect:/adminlogin";
        }

        request.getSession().setAttribute("successMessage", "User's notes have been deleted.");
        noteRepository.deleteByUserId(id);

        return "redirect:/admin";
    }
}
