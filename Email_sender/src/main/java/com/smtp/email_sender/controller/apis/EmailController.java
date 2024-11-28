package com.smtp.email_sender.controller.apis;

import com.smtp.email_sender.helper.CustomResponse;
import com.smtp.email_sender.helper.EmailRequest;
import com.smtp.email_sender.service.Impl.Email_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class EmailController {

    private Email_Service emailService;

    public EmailController(Email_Service emailService) {
        this.emailService = emailService;
    }

    //    send email
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail (@RequestBody EmailRequest request) {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(
                CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build()
        );
    }
    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendEmailWithFile (@RequestPart EmailRequest request, @RequestPart("file") MultipartFile file) throws IOException {
        emailService. sendEmailWithStream(request.getTo(),request.getSubject(), request.getMessage(),file.getInputStream());
        return ResponseEntity.ok(
                CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build()
        );
    }


}
