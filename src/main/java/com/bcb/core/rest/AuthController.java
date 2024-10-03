package com.bcb.core.rest;

import com.bcb.core.domain.AuthService;
import com.bcb.core.exception.BCBException;
import com.bcb.core.rest.model.LoginRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestBody credentials) {
        try {
            String phoneNumber = service.attemptLogin(credentials.getUsername(), credentials.getPassword());
            return new ResponseEntity<> (phoneNumber, HttpStatus.OK);
        } catch (BCBException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}
