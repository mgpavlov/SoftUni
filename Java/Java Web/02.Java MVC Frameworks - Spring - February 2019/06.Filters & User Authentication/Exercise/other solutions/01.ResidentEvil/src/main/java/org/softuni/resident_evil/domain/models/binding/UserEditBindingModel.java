package org.softuni.resident_evil.domain.models.binding;

import org.softuni.resident_evil.domain.entities.UserRole;

import java.util.Set;

public class UserEditBindingModel {
    private String username;

    private String email;

    private Set<UserRole> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
