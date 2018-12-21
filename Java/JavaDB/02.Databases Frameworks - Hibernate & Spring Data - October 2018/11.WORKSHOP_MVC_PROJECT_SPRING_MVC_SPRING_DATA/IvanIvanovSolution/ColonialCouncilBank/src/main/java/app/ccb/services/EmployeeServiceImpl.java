package app.ccb.services;

import app.ccb.constants.ResourcesPath;
import app.ccb.domain.dtos.EmployeeDto;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               Gson gson,
                               ModelMapper mapper,
                               ValidationUtil validationUtil,
                               FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {

        return this.employeeRepository.count() != 0;

    }

    @Override
    public String readEmployeesJsonFile() {
        return this.fileUtil.readFile(ResourcesPath.JSON.EMPLOYEES);
    }

    @Override
    public String importEmployees(String employees) {
        List<String> responseMessages = new ArrayList<>();
        try {

            EmployeeDto[] employeeDtos = this.gson.fromJson(employees, EmployeeDto[].class);
            List<Employee> successCreated = new ArrayList<>(employeeDtos.length);
            for (EmployeeDto employeeDto : employeeDtos) {

                if (this.validationUtil.isValid(employeeDto)) {
                    Employee employee = this.mapper.map(employeeDto, Employee.class);
                    successCreated.add(employee);
                    responseMessages.add(String.format("Successfully imported %s – %s.", "Employee", employeeDto.getFullName()));
                } else {
                    responseMessages.add("Error: Incorrect Data!");
                }
            }
            this.employeeRepository.saveAll(successCreated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessages.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String exportTopEmployees() {

        return this.employeeRepository.extractDb().stream().map(Employee::toString).collect(Collectors.joining("<br />"));
    }
}
