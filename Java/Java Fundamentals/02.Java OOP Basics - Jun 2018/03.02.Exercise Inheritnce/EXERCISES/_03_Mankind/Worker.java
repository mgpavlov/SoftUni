package _03_Inheritance.EXERCISES._03_Mankind;

import java.text.DecimalFormat;

public class Worker extends Human {

    private Double weekSalary;
    private Double workHoursPerDay;

    public Worker(String firstName, String lastName, Double salary, Double workingHours) throws Exception {
        super(firstName, lastName);
        this.setWeekSalary(salary);
        this.setWorkHoursPerDay(workingHours);
    }

    public void setWeekSalary(Double weekSalary) {
        if (weekSalary <= 10) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public void setWorkHoursPerDay(Double workHoursPerDay) {
        if (workHoursPerDay < 1 || workHoursPerDay > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workHoursPerDay = workHoursPerDay;
    }

    public Double getWeekSalary() {
        return this.weekSalary;
    }

    public Double getWorkHoursPerDay() {
        return this.workHoursPerDay;
    }

    @Override
    public String toString() {

        String y = String.format("%.2f", this.getWeekSalary());
        DecimalFormat df = new DecimalFormat("0.00");

        String x = String.format("%.2f", (this.getWeekSalary() / 7) / getWorkHoursPerDay());

        return "First Name: " + super.getFirstName() + "\n"
                + "Last Name: " + super.getLastName() + System.lineSeparator()
                + "Week Salary: " + y + "\n"
                + "Hours per day: " + df.format(getWorkHoursPerDay()) + "\n"
                + "Salary per hour: " + x + "\n";
    }
}
