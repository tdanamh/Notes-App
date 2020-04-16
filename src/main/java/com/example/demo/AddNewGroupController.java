package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddNewGroupController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/addNewGroup")
    public String addNewGroup(@RequestParam String name, @RequestParam(required = false) String emails, HttpServletRequest request) {
        // Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        // Check if group is empty
        if (emails == null) {
            request.getSession().setAttribute("error", true);
        } else {
            // Get user id in order to save the group by specific user
            String email = (String)request.getSession().getAttribute("user");
            User user = userRepository.findByEmail(email);
            String userId = user.id;
            groupRepository.save(new Group(name, userId, emails));
            request.getSession().setAttribute("groupAdded", true);
        }
        return "redirect:/groups";
    }
}
