package _03_Inheritance.EXERCISES._03_Mankind;

public class Student extends Human {
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) throws Exception {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    public void setFacultyNumber(String facultyNumber) {
        if (facultyNumber.length() < 5 || facultyNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facultyNumber = facultyNumber;
    }

    public String getFacultyNumber() {
        return this.facultyNumber;
    }
    @Override
    public String toString() {
        return "First Name: " + super.getFirstName() + "\n"
                + "Last Name: " + super.getLastName() + "\n"
                + "Faculty number: " + this.getFacultyNumber()+"\n";
    }
}
