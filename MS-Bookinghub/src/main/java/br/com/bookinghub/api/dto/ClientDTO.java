package br.com.bookinghub.api.dto;


import br.com.bookinghub.api.model.Client;
import br.com.bookinghub.api.model.Role;

import java.io.Serializable;
import java.time.LocalDate;

public class ClientDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private LocalDate birthdate;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ClientDTO() {
    }

    public ClientDTO(Long id, String firstName, String lastName, String email, String password, LocalDate birthdate, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.birthdate = birthdate;
    }

    public ClientDTO(Client client){

        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        password = client.getPassword();
        role = client.getRole();
        birthdate = client.getBirthdate();
    }
}
