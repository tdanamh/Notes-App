package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShortcutStatusController {
    boolean nToNotes = true;
    boolean pToProfile = false;

    @GetMapping("/shortcutStatus")
    public Map<String, Boolean> shortcutStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            Map<String, Boolean> shortcutMap = new HashMap<String, Boolean>();
            shortcutMap.put("nToNotes", nToNotes);
            shortcutMap.put("pToProfile", pToProfile);
            return shortcutMap;
        }
        return null;
    }
}
