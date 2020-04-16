package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GroupsController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public String groups(HttpServletRequest request, Model model) {
        // Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        if (request.getSession().getAttribute("groupAdded") != null) {
            model.addAttribute("successMessage", "Your group has been successfully added!");
            request.getSession().setAttribute("groupAdded", null);
        } else if (request.getSession().getAttribute("groupDeleted") != null) {
            model.addAttribute("successMessage", "Your group has been successfully deleted!");
            request.getSession().setAttribute("groupDeleted", null);
        } else if (request.getSession().getAttribute("error") != null) {
            model.addAttribute("successMessage", "You are trying to add an empty group!");
            request.getSession().setAttribute("error", null);
        }
        // Get all users for creating a new group
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        // Get all groups by current user id and send them to model
        String email = (String)request.getSession().getAttribute("user");
        User user = userRepository.findByEmail(email);
        String userId = user.id;
        List<Group> groups = groupRepository.findByUserId(userId);
        model.addAttribute("groups", groups);
        return "groups";
    }
}
