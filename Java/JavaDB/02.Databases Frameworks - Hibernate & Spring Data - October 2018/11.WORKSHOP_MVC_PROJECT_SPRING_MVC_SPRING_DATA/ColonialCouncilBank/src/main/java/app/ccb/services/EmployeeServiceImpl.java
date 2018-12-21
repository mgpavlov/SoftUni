package app.ccb.services;

import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static String EMPLOYEES_JSON_FILE_PATH = "C:\\Projects\\SoftUni\\Java\\JavaDB\\02.Databases Frameworks - Hibernate & Spring Data - October 2018\\ExamPreparation\\workshop1\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\employees.json";
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validator;
    private final ModelMapper modelMapper;



    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ValidationUtil validator, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;

    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        String employeesFileContent = this.fileUtil.readFile(EMPLOYEES_JSON_FILE_PATH);
        return employeesFileContent;
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();

        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {
            if (!this.validator.isValid(employeeImportDto)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            Branch branch = this.branchRepository.findByName(employeeImportDto.getBranchName()).orElse(null);
            Employee employeeEntity = this.modelMapper.map(employeeImportDto, Employee.class);
            employeeEntity.setFirstName(employeeImportDto.getFullName().split("\\s+")[0]);
            employeeEntity.setLastName(employeeImportDto.getFullName().split("\\s+")[1]);

            employeeEntity.setStartedOn(LocalDate.parse(employeeImportDto.getStartedOn()));
            employeeEntity.setBranch(branch);


            this.employeeRepository.saveAndFlush(employeeEntity);

            importResult.append(String.format("Successfully imported Employee – %s %s.", employeeEntity.getFirstName(), employeeEntity.getLastName()));
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        List<Employee> employeesWithClients1 = this.employeeRepository.getEmployees();
        /*List<Employee> employeesWithClients2 = this.employeeRepository.getEmployeesByClientsNotNullAndOrderByClientsDescAndIdAsc();*/
        List<Employee> employeesWithClients3 = this.employeeRepository.extractTopEmployees();

        StringBuilder exportResult = new StringBuilder();
        for (Employee employee : employeesWithClients1) {
            exportResult.append(String.format("Full Name: %s", employee.getFirstName(), employee.getLastName())).append(System.lineSeparator())
                    .append(String.format("Salary: %.2f", employee.getSalary())).append(System.lineSeparator())
                    .append(String.format("Started On: %s", employee.getStartedOn().toString())).append(System.lineSeparator())
                    .append("Clients:").append(System.lineSeparator());
            for (Client client : employee.getClients()) {
                exportResult.append("   ").append(client.getFullName()).append(System.lineSeparator());
            }
            exportResult.append(System.lineSeparator());
        }
        return exportResult.toString().trim();
    }
}
