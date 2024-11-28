package com.smtp.email_sender;

import com.smtp.email_sender.service.Impl.Email_Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
    private Email_Service emailService;

    @Test
    void emailSenderTest() {
        System.out.println("sending mail");
        emailService.sendEmail("shreyabajaj2310@gmail.com", "this is subject", "this is the body os the demo mail");
    }

    @Test
    void sendHtmlInMail() {
        String Html = "" +
                "<h1 style='color:red; border:2px solid black;'>Hello everyone finally i am atleast a newbie in codeforces</h1>" + "";
        emailService.sendEmailWithHtml("shreyasaw112@gmail.com", "this is subject", Html);
    }

    @Test
    void sendEmailWithFile() {
        emailService.sendEmailWithFile("shreyabajaj2310@gmail.com",
                "this is a email containing file",
                "this is a random file",
                new File("D:\\Spring Boot\\PROJECTS\\email_sender\\Email_sender\\src\\main\\resources\\static\\community dev.docx")

        );
    }

    @Test
    void sendEmailWithStream() {
        File file = new File("C:/Users/shrey/OneDrive/Pictures/Screenshots/Screenshot 2024-10-06 120252.png");
        try {
            InputStream is = new FileInputStream(file);
            emailService.sendEmailWithStream("shreyabajaj2310@gmail.com",
                    "this is a email containing file",
                    "this is a random file", is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
