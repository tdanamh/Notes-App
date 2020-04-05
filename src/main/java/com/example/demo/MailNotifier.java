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

    public void sendHtmlEmail(String content, String title, String text, String receiver) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(receiver);

        helper.setSubject("New note added");
//        For simple text default = text/plain
//        helper.setText("Check attachment for image!");

//         true = text/html
        helper.setText(content + "<br>" +
                "Title: " + title + "<br>" +
                "Content: " + text + "<br>",
               true);

        // For attachment
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        javaMailSender.send(msg);

    }
}
