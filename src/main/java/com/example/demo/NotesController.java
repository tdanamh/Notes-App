package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
public class NotesController {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/notes")
    public String notes(Model model, HttpServletRequest request) {
        // Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        if (request.getSession().getAttribute("noteAdded") != null) {
            model.addAttribute("successMessage", "Your note has been successfully added!");
            request.getSession().setAttribute("noteAdded", null);
        } else if (request.getSession().getAttribute("noteDeleted") != null) {
            model.addAttribute("successMessage", "Your note has been successfully deleted!");
            request.getSession().setAttribute("noteDeleted", null);
        } else if (request.getSession().getAttribute("noteShared") != null) {
            model.addAttribute("successMessage", "Your note has been successfully shared!");
            request.getSession().setAttribute("noteShared", null);
        }  else if (request.getSession().getAttribute("noteSharedToGroup") != null) {
            model.addAttribute("successMessage", "Your note has been successfully shared to a group and added to their private notes!");
            request.getSession().setAttribute("noteSharedToGroup", null);
        } else if (request.getSession().getAttribute("errorEmpty") != null) {
            model.addAttribute("successMessage", "You are trying to share a note without a receiver!");
            request.getSession().setAttribute("errorEmpty", null);
        }  else if (request.getSession().getAttribute("errorTooMany") != null) {
            model.addAttribute("successMessage", "You can't share a note both to email and group!");
            request.getSession().setAttribute("errorTooMany", null);
        } else if (request.getSession().getAttribute("errorInvalidGroupName") != null) {
            model.addAttribute("successMessage", "You typed an invalid group name!");
            request.getSession().setAttribute("errorInvalidGroupName", null);
        }

        // Get current user id
        String email = (String)request.getSession().getAttribute("user");
        User user = userRepository.findByEmail(email);
        String userId = user.id;

        // Get all notes by user id and send them to model
        List<Note> notes = noteRepository.findByUserId(userId);

        // Get all categories by user id and send them to model
        HashSet<String> categories = new HashSet<>();
        for (Note note: notes) {
            if (note.category != null) {
                categories.add(note.category);
            }
        }
        model.addAttribute("categories", categories);

        if (request.getParameter("filter") != null && !request.getParameter("filter").equals("")) {
            notes = noteRepository.findByUserIdAndCategory(userId, request.getParameter("filter"));
        }

        // Reverse list
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
        return "notes";
    }

}
