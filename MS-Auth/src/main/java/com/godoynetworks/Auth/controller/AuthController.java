package com.godoynetworks.Auth.controller;

import com.godoynetworks.Auth.DTO.AuthenticationResponse;
import com.godoynetworks.Auth.DTO.RegisterRequest;
import com.godoynetworks.Auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") //Mudar para IP da VPS em produção
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request) {
        AuthenticationResponse authReponse = authService.register(request);
        return new ResponseEntity<>(authReponse, HttpStatus.CREATED);
    }
}
