package softuni.residentevil.domain.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
  private static final String USERNAME_ERROR = "Username should be 3 - 12 characters long.";
  private static final String PASSWORD_ERROR = "Password should be 3 - 12 characters long.";
  private static final String EMAIL_ERROR = "Invalid input.";
  private String username;
  private String password;
  private String confirmPassword;
  private String email;

  public UserRegisterBindingModel() {
  }

  @NotNull
  @Size(min = 3, max = 12, message = USERNAME_ERROR)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @NotNull
  @Size(min = 3, max = 12, message = PASSWORD_ERROR)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @NotNull
  @Size(min = 3, max = 12, message = PASSWORD_ERROR)
  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  @NotNull
  @Email(message = EMAIL_ERROR)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


}
