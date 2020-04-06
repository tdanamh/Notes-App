package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShortcutStatusController {
    boolean nToNotes = true;
    boolean pToProfile = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShortcutRepository shortcutRepository;

    @GetMapping("/shortcutStatus")
    public Map<String, Boolean> shortcutStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            String email = request.getSession().getAttribute("user").toString();
            User user = userRepository.findByEmail(email);
            String userId = user.id;
            Shortcut shortcut = shortcutRepository.findByUserId(userId);

            Map<String, Boolean> shortcutMap = new HashMap<String, Boolean>();
            shortcutMap.put("nToNotes", shortcut.nToNotes);
            shortcutMap.put("pToProfile", shortcut.pToProfile);
            return shortcutMap;
        }
        return null;
    }
}
