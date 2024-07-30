package com.godoynetworks.Auth.controller;

import com.godoynetworks.Auth.repository.FeignUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    FeignUserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<?>> getAllUsers() {
        return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
    }

}
