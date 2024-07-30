package com.godoynetworks.Auth.DTO;

import com.godoynetworks.Auth.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class RegisterRequest {

    /*
    This class is a DTO class, identical to DTO in CrudAPI
     */


    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 2, max = 20, message = "First name must be minimum 2 to maximum 20 characters")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 20, message = "Password must be between 8 to 20 characters")
    private String password;

    private Role role;

}
