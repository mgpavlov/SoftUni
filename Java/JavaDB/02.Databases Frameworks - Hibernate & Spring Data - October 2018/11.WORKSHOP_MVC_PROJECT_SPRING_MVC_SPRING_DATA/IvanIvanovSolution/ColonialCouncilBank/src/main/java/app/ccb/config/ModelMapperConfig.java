package app.ccb.config;

import app.ccb.domain.dtos.BankAccountDto;
import app.ccb.domain.dtos.ClientDto;
import app.ccb.domain.dtos.EmployeeDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

    private ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public ModelMapperConfig(BranchRepository branchRepository,
                             EmployeeRepository employeeRepository,
                             ClientRepository clientRepository,
                             BankAccountRepository bankAccountRepository) {
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public void configure(ModelMapper mapper) {
        this.modelMapper = mapper;
        if (mapper != null) {
            this.initMappings();
        }
    }

    private void initMappings() {
        this.configEmployeeDtoToEmployee();
        this.configClientDtoToClient();
        this.configBankAccountDtoToBankAccount();
    }

    private void configEmployeeDtoToEmployee() {
        this.modelMapper
                .createTypeMap(EmployeeDto.class, Employee.class)
                .setConverter(mappingContext -> {
                    Employee employee = mappingContext.getDestination();
                    EmployeeDto employeeDto = mappingContext.getSource();
                    employee.setFirstName(employeeDto.getFullName().split("\\s+")[0]);
                    employee.setLastName(employeeDto.getFullName().split("\\s+")[1]);
                    employee.setSalary(employeeDto.getSalary());
                    employee.setStartedOn(employeeDto.getStartedOn());
                    Branch branch = branchRepository.findOneByName(employeeDto.getBranchName());
                    employee.setBranch(branch);
                    branch.getEmployees().add(employee);
                    return employee;

                });
    }

    private void configClientDtoToClient() {
        this.modelMapper
                .createTypeMap(ClientDto.class, Client.class)
                .setConverter(mappingContext -> {
                    Client client = mappingContext.getDestination();
                    ClientDto clientDto = mappingContext.getSource();
                    client.setAge(clientDto.getAge());
                    client.setFullName(clientDto.getFirstName() + " " + clientDto.getLastName());
                    String[] employeeNames = clientDto.getAppointedEmployee().split("\\s+");
                    Employee employee = this.employeeRepository.findOneByFirstNameAndLastName(employeeNames[0], employeeNames[1]);
                    client.getEmployees().add(employee);
                    if (employee != null) {
                        employee.getClients().add(client);
                    }
                    return client;

                });
    }

    private void configBankAccountDtoToBankAccount() {
        this.modelMapper
                .createTypeMap(BankAccountDto.class, BankAccount.class)
                .setConverter(mappingContext -> {
                    BankAccount bankAccount = mappingContext.getDestination();
                    BankAccountDto bankAccountDto = mappingContext.getSource();
                    bankAccount.setAccountNumber(bankAccountDto.getAccountNumber());
                    bankAccount.setBalance(bankAccountDto.getBalance());
                    Client client = this.clientRepository.findFirstByFullName(bankAccountDto.getClient());
                    if (client != null) {
//                        bankAccount = this.bankAccountRepository.save(bankAccount);
                        bankAccount.setClient(client);
//                        client.setBankAccount(bankAccount);
//                        this.clientRepository.save(client);
                    }
                    return bankAccount;
                });
    }
}
