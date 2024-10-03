package com.immunisation.immunisationcode.CONTROLLER;

import com.immunisation.immunisationcode.SERVICES.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class emailController {

@Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/test-email")
    public ResponseEntity<String> sendTestEmail() {
        emailSenderService.sendSimpleEmail("solomonndimu75@gmail.com", "Test Subject", "Test Body");
        return ResponseEntity.ok("Test email sent!");
    }

}
