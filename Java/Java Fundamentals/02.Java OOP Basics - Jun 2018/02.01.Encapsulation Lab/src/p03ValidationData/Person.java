package p03ValidationData;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;


    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void increaseSalary(double bonus) {
        if (this.age<30){
            this.salary = this.salary*(1 + bonus/200);
        }else {
            this.salary = this.salary*(1+ bonus/100);
        }
    }

    public void setFirstName(String firstName) {
        if (firstName.length() <3){
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() <3){
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age<=0){
            throw new IllegalArgumentException("Age cannot be zero or negative integer");

        }
        this.age = age;
    }

    public void setSalary(double salary) {
        if (salary< 460){
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " gets " + this.salary + " leva";
    }
}
