package com.godoynetworks.Auth.repository;

import com.godoynetworks.Auth.DTO.RegisterRequest;
import com.godoynetworks.Auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MS-SAFEROOMAPI")
public interface FeignUserRepository {

    @GetMapping("/clients")
    public <T> List<T> getAllUsers();

    @PostMapping("/clients")
    public RegisterRequest addClient (RegisterRequest request);

    @GetMapping("/clients/exists")
    public boolean existsByEmail (@RequestParam("email") String email);

    @GetMapping("/clients/email")
    public User loadUserByUsername(@RequestParam("email") String email);

}
