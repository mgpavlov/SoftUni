package app.ccb.services;

public interface EmployeeService {

    Boolean employeesAreImported();

    String readEmployeesJsonFile();

    String importEmployees(String employees);

    String exportTopEmployees();
}
