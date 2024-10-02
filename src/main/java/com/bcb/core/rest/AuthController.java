package com.bcb.core.rest;

import com.bcb.core.domain.AuthService;
import com.bcb.core.exception.BCBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        try {
            String phoneNumber = service.attemptLogin();
            return new ResponseEntity<> (phoneNumber, HttpStatus.OK);
        } catch (BCBException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
