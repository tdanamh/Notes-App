package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShortcutsController {
    @Autowired
    private ShortcutRepository shortcutRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/shortcuts")
    public String shortcuts(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            String email = request.getSession().getAttribute("user").toString();
            User user = userRepository.findByEmail(email);

            Shortcut shortcuts = shortcutRepository.findByUserId(user.id);

            model.addAttribute("notesShortcut", shortcuts.notesShortcut);
            model.addAttribute("profileShortcut", shortcuts.profileShortcut);
            return "shortcuts";
        }
        return "redirect:/index";
    }
}
