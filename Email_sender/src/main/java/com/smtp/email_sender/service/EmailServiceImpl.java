package com.smtp.email_sender.service;
import com.smtp.email_sender.service.Impl.Email_Service;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements Email_Service {
    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);


    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("shreyabajaj589@gmail.com");
        mailSender.send(mailMessage);
        logger.info("Email sent successfully");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("shreyabajaj589@gmail.com");
        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("shreyabajaj589@gmail.com");
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("shreyabajaj589@gmail.com");
            helper.setText(message,true);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void sendEmailWithStream(String to, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("shreyabajaj589@gmail.com");
            helper.setText(message,true);
            File file=new File("test.png");
            Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


//    code to receive all emails
//    @Override
//    public List<Messages> getInboxMessages() {
//        Properties configuration = new Properties();
//
//        configuration.setProperty("mail.store.protocol","");
//        configuration.setProperty("mail.imap.host", "smtp.gmail.com");
//        configuration.setProperty("mail.imap.port", "");
//
//        Session session = Session.getDefaultInstance(configuration);
//        try{
//            Store store=session.getStore();
//
//            Folder inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_ONLY);
//
//
//
//        }
//        catch (NoSuchProviderException e) {
//            throw new RuntimeException(e);
//        }
//        return List.of();
//    }
}
