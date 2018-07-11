package p03Mankind;

public class Worker extends Human {
    private double weekSalary;
    private double workHoursPerDay;

    public Worker(String firstName, String lastName, double weekSalary, double workHoursPerDay) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkHoursPerDay(workHoursPerDay);
    }

    @Override
    public void setLastName(String lastName) {
        if (lastName.length() < 4){
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }
        super.setLastName(lastName);
    }

    public double getWeekSalary() {
        return this.weekSalary;
    }

    public void setWeekSalary(double weekSalary) {
        if (weekSalary < 11){
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public double getWorkHoursPerDay() {
        return this.workHoursPerDay;
    }

    public void setWorkHoursPerDay(double workHoursPerDay) {
        if (workHoursPerDay <1 ||workHoursPerDay > 12){
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workHoursPerDay = workHoursPerDay;
    }

    public double getSalaryPerHour() {
        return this.getWeekSalary()/(this.getWorkHoursPerDay()*7);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("First Name: %s%n", this.getFirstName()))
                .append(String.format("Last Name: %s%n", this.getLastName()))
                .append(String.format("Week Salary: %.2f%n", this.getWeekSalary()))
                .append(String.format("Hours per day: %.2f%n", this.getWorkHoursPerDay()))
                .append(String.format("Salary per hour: %.2f%n", this.getSalaryPerHour()));
        return sb.toString();
    }
}
