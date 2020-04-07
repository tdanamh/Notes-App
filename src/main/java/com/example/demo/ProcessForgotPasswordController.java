package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class ProcessForgotPasswordController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailNotifier mailNotifier;

    @PostMapping("/processForgotPassword")
    public String processForgotPassword(@RequestParam String email, Model model, HttpServletRequest request)
    {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            try {
                user.password = generateRandomPassword(8);
                userRepository.save(user);
                mailNotifier.sendHtmlEmail("Your password has been reset!",  user.password, "You can login using your new password.", email);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("message", "An email was sent at your email address with the new password.");
        } else {
            request.getSession().setAttribute("message", "We didn't find an account registered with your email. Maybe you would like to sign up.");
        }
        return "redirect:/forgotPassword";
    }

    private String generateRandomPassword(int size)
    {
        Random random = new Random();
        StringBuilder newPassword = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            newPassword.append(random.nextInt(10));
        }
        return newPassword.toString();
    }
}
