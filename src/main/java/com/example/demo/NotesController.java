package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class NotesController {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/notes")
    public String notes(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {

            if (request.getSession().getAttribute("noteAdded") != null) {
                model.addAttribute("successMessage", "Your note has been successfully added!");
                request.getSession().setAttribute("noteAdded", null);
            } else {
                if (request.getSession().getAttribute("noteDeleted") != null) {
                    model.addAttribute("successMessage", "Your note has been successfully deleted!");
                    request.getSession().setAttribute("noteDeleted", null);
                }
            }
            // Get current user id
            String email = request.getSession().getAttribute("user").toString();
            User user = userRepository.findByEmail(email);
            String userId = user.id;

            // Get all notes by user id and send them to model
            List<Note> notes = noteRepository.findByUserId(userId);
            // Reverse list
            Collections.reverse(notes);
            model.addAttribute("notes", notes);

            return "notes";
        }
        return "redirect:/index";
    }
}
