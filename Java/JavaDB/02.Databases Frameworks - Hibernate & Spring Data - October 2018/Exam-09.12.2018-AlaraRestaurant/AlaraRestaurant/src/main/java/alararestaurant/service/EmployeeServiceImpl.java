package alararestaurant.service;

import alararestaurant.common.Constants;
import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(Constants.EMPLOYEES_JSON_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();
        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);


        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {
            Position positionEntity = this.positionRepository.findByName(employeeImportDto.getPosition()).orElse(null);
            if (!this.validationUtil.isValid(employeeImportDto)) {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                continue;
            }

            Employee employeeEntity = this.modelMapper.map(employeeImportDto, Employee.class);
            if (positionEntity == null) {
                positionEntity = new Position();

                positionEntity.setName(employeeImportDto.getPosition());
                positionEntity.getEmployees().add(employeeEntity);

                if (!this.validationUtil.isValid(positionEntity)) {
                    importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

            }
            positionEntity.getEmployees().add(employeeEntity);
            this.positionRepository.saveAndFlush(positionEntity);
            
            employeeEntity.setPosition(positionEntity);
            
            this.employeeRepository.saveAndFlush(employeeEntity);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, employeeEntity.getName())).append(System.lineSeparator());
        }
        return importResult.toString().trim();
    }
}
