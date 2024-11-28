package com.smtp.email_sender.helper;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private String message;
    private HttpStatus httpStatus;
    private boolean success=false;
}
