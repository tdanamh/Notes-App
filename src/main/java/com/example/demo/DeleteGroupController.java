package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteGroupController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/deleteGroup")
    public String deleteGroup(@RequestParam String id, HttpServletRequest request) {
        // Secure
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/index";
        }
        // Delete the group by id
        groupRepository.deleteById(id);
        request.getSession().setAttribute("groupDeleted", true);
        return "redirect:/groups";
    }
}
