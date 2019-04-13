package org.softuni.onlinegrocery.util.constants;

public final class ExceptionMessages {

    private ExceptionMessages() {
    }
    public static final String INCORRECT_USERNAME = "Username should be between 3 and 20 symbols.";
    public static final String INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG = "Cannot be empty, should be at least 3 symbols.";
    public static final String INCORRECT_PASSWORD = "Cannot be empty, should be at least 3 symbols.";
    public static final String INCORRECT_ADDRESS = "Cannot be empty, should be at least 5 symbols.";
    public static final String INCORRECT_EMAIL = "Email should be valid, compliant with RFC822.";

    public static final String NON_UNIQUER_USERNAME_EX_MSG = "Username is already taken! Please enter new one.";
    public static final String NON_UNIQUER_EMAIL_EX_MSG = "Email is already taken! Please enter new one.";

    public static final String CAR_NOT_FOUND_EX_MSG = "Car not found.";
    public static final String INVALID_CAR_SERVICE_MODEL_EX_MSG = "Invalid car service model.";


    public static final String INVALID_NEWS_SERVICE_MODEL_EX_MSG = "Invalid news service model.";
}
