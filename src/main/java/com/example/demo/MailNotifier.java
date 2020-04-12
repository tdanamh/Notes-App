package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class MailNotifier {
    @Autowired
    private JavaMailSender javaMailSender;

    public void noteNotifierEmail(String subject, String content, String title, String text, String receiver) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(receiver);
        helper.setSubject(subject);

        // true = text/html
        helper.setText(content + "<br>" +
                "Title: " + title + "<br>" +
                "Content: " + text + "<br>",
               true);

        javaMailSender.send(msg);
    }
    public void forgotPasswordEmail(String subject, String content, String receiver) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(receiver);
        helper.setSubject(subject);

        // true = text/html
        helper.setText(content, true);

        javaMailSender.send(msg);
    }
}
