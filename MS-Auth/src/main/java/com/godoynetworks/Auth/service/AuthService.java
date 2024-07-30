package com.godoynetworks.Auth.service;

import com.godoynetworks.Auth.DTO.AuthenticationRequest;
import com.godoynetworks.Auth.DTO.AuthenticationResponse;
import com.godoynetworks.Auth.DTO.RegisterRequest;
import com.godoynetworks.Auth.exception.ValidationException;
import com.godoynetworks.Auth.model.User;
import com.godoynetworks.Auth.repository.FeignUserRepository;
import com.godoynetworks.Auth.security.JwtService;
import com.godoynetworks.Auth.validation.RegisterRequestValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private FeignUserRepository feignUserRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public AuthenticationResponse authenticate (AuthenticationRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User userDetails = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().accessToken(token).build();
    }


    public AuthenticationResponse register(RegisterRequest request) {
        validateRequest(request);

        if (feignUserRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException(List.of("Email already registered"), 409);
        }
          RegisterRequest registeredUser = feignUserRepository.addClient(request);
          User user = new ModelMapper().map(registeredUser, User.class);
          String jwtToken = jwtService.generateToken(user);
          return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    private void validateRequest(RegisterRequest request) {
        DataBinder binder = new DataBinder(request);
        binder.addValidators(new RegisterRequestValidator());
        binder.validate(request);

        BindingResult result = binder.getBindingResult();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            throw new ValidationException(errors, 400);
        }
    }
}
