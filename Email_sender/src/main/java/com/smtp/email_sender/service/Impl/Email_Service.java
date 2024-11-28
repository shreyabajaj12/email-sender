package com.smtp.email_sender.service.Impl;


import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface Email_Service {

//    send email to single person
    void sendEmail(String to, String subject, String message);
//    to multiple person
    void sendEmail(String []to, String subject, String message);
//    send email with html
    void sendEmailWithHtml(String to, String subject, String htmlContent);
//    void send email with file
    void sendEmailWithFile(String to, String subject,String message, File file);

    void sendEmailWithStream(String to, String subject, String message, InputStream is);
//    dynamic file sending
//    void sendEmailWithStream(String to, String subject, String message, InputStream is);
//
//
//    List<Messages> getInboxMessages();

}
