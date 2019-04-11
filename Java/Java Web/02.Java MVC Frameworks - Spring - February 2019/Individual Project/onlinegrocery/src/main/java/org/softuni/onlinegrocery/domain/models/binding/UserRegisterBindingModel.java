package org.softuni.onlinegrocery.domain.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    private static final String VIRUS_NAME_LENGTH = "Virus name cannot be empty, should be between 3 and 10 symbols!";
    private static final String VIRUS_DESCRIPTION_LENGTH = "Virus description cannot be empty, should be between 5 and 100 symbols!";
    private static final String VIRUS_SIDE_EFFECTS_LENGTH = "Virus side effects should have a maximum of 50 symbols!";
    private static final String VIRUS_CREATOR_TEXT = "Virus Creator should be either Corp or corp!";
    private static final String VIRUS_DEADLY_NULL = "Deadly property cannot be null!";
    private static final String VIRUS_CURABLE_NULL = "Curable property cannot be null!";
    private static final String VIRUS_MUTATION_NULL = "Mutation cannot be null!";
    private static final String VIRUS_TURNOVER_RATE_NULL = "Turnover rate cannot be null!";
    private static final String VIRUS_TURNOVER_RATE_RANGE = "Turnover rate should be between 0 and 100!";
    private static final String VIRUS_HOURS_UNTIL_MUTATION_NULL = "Hours until mutation cannot be null!";
    private static final String VIRUS_HOURS_UNTIL_MUTATION_RANGE = "Hours until mutation should be between 1 and 12!";
    private static final String VIRUS_MAGNITUDE_NULL = "Magnitude cannot be null!";
    private static final String VIRUS_RELEASED_ON_DATE_NULL = "Release date cannot be null!";
    private static final String VIRUS_RELEASED_ON_DATE_INVALID = "Release date should be in the past!";
    private static final String VIRUS_CAPITALS_EMPTY = "You must select capitals!";

    private String username;
    private String password;
    private String confirmPassword;
    private String address;
    private String email;

    public UserRegisterBindingModel() {

    }

    @NotNull
    @Size(min = 1, max = 10, message = VIRUS_NAME_LENGTH)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Size(min = 1, max = 10, message = VIRUS_DESCRIPTION_LENGTH)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Size(min = 1, max = 10, message = VIRUS_DESCRIPTION_LENGTH)
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull
    @Size(min = 1, max = 10, message = VIRUS_DESCRIPTION_LENGTH)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull
    @Size(min = 1, max = 10, message = VIRUS_DESCRIPTION_LENGTH)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
