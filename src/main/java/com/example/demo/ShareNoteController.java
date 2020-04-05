package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ShareNoteController {
    @Autowired
    private NoteRepository repository;
    @Autowired
    private MailNotifier mailSender;

    @PostMapping("/shareNote")
    public String shareNote(@RequestParam String id, @RequestParam String shareEmail, HttpServletRequest request) {
//        Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        Optional<Note> note = repository.findById(id);
        if (note.isPresent()) {
            Note existingNote = note.get();
            String noteTitle = existingNote.title;
            String noteText = existingNote.text;
            try {
                String content = "Hello from Notes App. The user " + request.getSession().getAttribute("user") +
                        " shared with you the following note: ";
                mailSender.sendHtmlEmail(content, noteTitle, noteText, shareEmail);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.getSession().setAttribute("noteDeleted", true);
        return "redirect:/notes";
    }
}
