package _03_Inheritance.EXERCISES._03_Mankind;

public class Human {
    private String firstName;
    private String lastName;


    public Human(String firstName, String lastName) throws Exception {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    private void setFirstName(String firstName) throws Exception {
        if (Character.isLowerCase(firstName.charAt(0))) {
            throw new Exception("Expected upper case letter!Argument: firstName");
        }
        if (firstName.length() < 4) {
            throw new Exception("Expected length at least 4 symbols!Argument: firstName");
        }
        this.firstName = firstName;
    }

    private void setLastName(String lastName) throws Exception {
        if (Character.isLowerCase(lastName.charAt(0))) {
            throw new Exception("Expected upper case letter!Argument: lastName");
        }
        if (lastName.length() < 3) {
            throw new Exception("Expected length at least 3 symbols!Argument: lastName");
        }
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
