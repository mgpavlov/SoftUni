package p04CompanyRoster1;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private static final int DEFAULT_AGE = -1;
    private static final String DEFAULT_EMAIL  = "n/a";
    private String name;
    private double salary;
    private String position;
    private String department;
    private String email;
    private int age;
    public static List<Employee> employees =  new ArrayList<>();

    public Employee(String name, double salary, String position, String department) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = DEFAULT_EMAIL;
        this.age = DEFAULT_AGE;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static void setEmployees(Employee employee) {
       employees.add(employee);
    }
}
