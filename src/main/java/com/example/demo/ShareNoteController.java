package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ShareNoteController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MailNotifier mailSender;

    @PostMapping("/shareNote")
    public String shareNote(@RequestParam String id, @RequestParam(required = false) String shareEmail, @RequestParam(required = false) String shareGroupName, HttpServletRequest request) {
        // Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        // Get note information
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            Note existingNote = note.get();
            String noteTitle = existingNote.title;
            String noteCategory = existingNote.category;
            String noteText = existingNote.text;
            String noteDueDate = existingNote.dueDate;

            // Check form
            if (shareEmail.equals("") && shareGroupName.equals("")) {
                request.getSession().setAttribute("errorEmpty", true);
            } else if (!shareEmail.equals("") && !shareGroupName.equals("")) {
                request.getSession().setAttribute("errorTooMany", true);
            } else {
                String userEmail = (String)request.getSession().getAttribute("user");
                String subject = "Shared note";
                String content = "Hello from Notes App. The user " + userEmail + " shared with you the following note: ";

                // Send by email to one person
                if (!shareEmail.equals("")) {
                    try {
                        mailSender.noteNotifierEmail(subject, content, noteTitle, noteText, shareEmail);
                    } catch (MessagingException | IOException e) {
                        e.printStackTrace();
                    }
                    request.getSession().setAttribute("noteShared", true);
                } else {
                    // Check if group name exists for specific user
                    User user = userRepository.findByEmail(userEmail);
                    String userId = user.id;
                    List<Group> groups = groupRepository.findByUserIdAndName(userId, shareGroupName);
                    if (groups.isEmpty()) {
                        request.getSession().setAttribute("errorInvalidGroupName", true);
                    } else {
                        // Send by email to a group of persons
                        for (Group group: groups) {
                            String[] persons = group.participants.split(",");
                            for(String person : persons) {
                                if (!person.equals(userEmail)) {
                                    try {
                                        mailSender.noteNotifierEmail(subject, content, noteTitle, noteText, person);
                                    } catch (MessagingException | IOException e) {
                                        e.printStackTrace();
                                    }
                                    // Get person user id in order to save in their notes database
                                    User shareUser = userRepository.findByEmail(person);
                                    String shareUserId = shareUser.id;
                                    noteRepository.save(new Note(shareUserId, noteTitle, noteCategory, noteText, noteDueDate));
                                }
                            }
                        }
                        request.getSession().setAttribute("noteSharedToGroup", true);
                    }
                }
            }
        }
        return "redirect:/notes";
    }
}
