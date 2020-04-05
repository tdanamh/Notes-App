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
    private MailNotifier mailSender;

    @PostMapping("/addNote")
    public String addNote(@RequestParam String title, @RequestParam String text, HttpServletRequest request) {
//        Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        repository.save(new Note(title, text));
        request.getSession().setAttribute("noteAdded", true);
        try {
            String content = "Hello. You just added a new note.";
            mailSender.sendHtmlEmail(content, title, text, request.getSession().getAttribute("user").toString());
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return "redirect:/notes";
    }
}
