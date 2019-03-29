package org.softuni.resident_evil.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.softuni.resident_evil.domain.entities.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserServiceModel {

    private String id;

    private String username;

    private String password;

    private String email;

    private Set<UserRole> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @Length(min = 5)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @Length(min = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(regexp = "^(.+)@(.+)$")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }
}
