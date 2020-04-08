package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ManageNotesController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/manageNotes")
    public String manageNotes(HttpServletRequest request, Model model) {
//        Secure
        if (request.getSession().getAttribute("admin") == null) {
            return "redirect:/adminlogin";
        }
        if (request.getSession().getAttribute("noteDeleted") != null) {
            model.addAttribute("successMessage", request.getSession().getAttribute("noteDeleted"));
            request.getSession().removeAttribute("noteDeleted");
        }
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "managenotes";
    }
}
