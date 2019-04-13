package org.softuni.onlinegrocery.util.constants;

public final class AppConstants {

    private AppConstants() {
    }
    /**
     * Regex for validating e-mails. It's totally compliant with RFC822
     * and accepts IP address and server names (for intranet purposes)
     *
     * john@somewhere.com // valid
     * john.foo@somewhere.com // valid
     * john.foo+label@somewhere.com // valid (with +label - Gmail accepts it!)
     * john@192.168.1.10 // valid (with IP addresses)
     * john+label@192.168.1.10 // valid (with +label and IP address)
     * john.foo@someserver // valid (with no first domain level)
     * JOHN.FOO@somewhere.com // valid (case insensitive)
     * @someserver // invalid
     * @someserver.com // invalid
     * john@. // invalid
     * .@somewhere.com // invalid
     */
    public static final String VALID_EMAIL_ADDRESS_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

    /** PASSWORD_VALIDATION_REGEX =>
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * (?=\S+$)          # no whitespace allowed in the entire string
     * .{8,}             # anything, at least eight places though
     * $                 # end-of-string
     */
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /** USERS ROLES **/
    public static final String ROLE_ROOT_ADMIN = "ROOT_ADMIN";
    public static final String ROOT_ADMIN = "ROOT ADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ADMIN = "ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static final String MODERATOR = "MODERATOR";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String USER = "USER";

}
