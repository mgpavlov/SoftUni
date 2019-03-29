package softuni.residentevil.domain.models.service;

import softuni.residentevil.domain.entities.Role;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {
  private String id;
  private String username;
  private String password;
  private String email;
  private Set<Role> roles;

  public UserServiceModel() {
    this.roles = new HashSet<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
