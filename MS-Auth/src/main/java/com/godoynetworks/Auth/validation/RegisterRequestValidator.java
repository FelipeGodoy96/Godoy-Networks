package com.godoynetworks.Auth.validation;

import com.godoynetworks.Auth.DTO.RegisterRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegisterRequestValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest request = (RegisterRequest) target;

        if (request.getFirstName() != null && request.getFirstName().contains(" ")) {
            errors.rejectValue("firstName", "fistname.nospaces", "First name cannot contain spaces");
        }

        if (request.getEmail() != null && request.getEmail().contains(" ")) {
            errors.rejectValue("email", "email.nospaces", "Email cannot contain spaces" );
        }


    }
}
