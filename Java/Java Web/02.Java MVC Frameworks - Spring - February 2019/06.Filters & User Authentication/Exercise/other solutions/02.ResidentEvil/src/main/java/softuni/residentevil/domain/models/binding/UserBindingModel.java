package softuni.residentevil.domain.models.binding;

import softuni.residentevil.domain.entities.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserBindingModel {
  private static final String INCORRECT_SIZE_ERR = "You must select at least one role.";
  private String id;
  private String username;
  private Set<Role> roles;

  public UserBindingModel() {
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

  @NotNull
  @Size(min = 1, message = INCORRECT_SIZE_ERR)
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
