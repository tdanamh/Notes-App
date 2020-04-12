package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AddNoteController {
    @Autowired
    private NoteRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailNotifier mailSender;

    @PostMapping("/addNote")
    public String addNote(@RequestParam String title, @RequestParam String category, @RequestParam String text, HttpServletRequest request) {
//        Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        // Get user id in order to save the note by specific user
        String email = request.getSession().getAttribute("user").toString();
        User user = userRepository.findByEmail(email);
        String userId = user.id;
        repository.save(new Note(userId, title, category, text));

        request.getSession().setAttribute("noteAdded", true);
        try {
            String content = "Hello. You just added a new note.";
            String subject = "New note";
            mailSender.noteNotifierEmail(subject, content, title, text, email);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return "redirect:/notes";
    }
}
