package app.ccb.services;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.entities.Card;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final static String CLIENTS_JSON_FILE_PATH = "C:\\Projects\\SoftUni\\Java\\JavaDB\\02.Databases Frameworks - Hibernate & Spring Data - October 2018\\ExamPreparation\\workshop1\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\clients.json";
    private final FileUtil fileUtil;
    private final ClientRepository clientRepository;
    private final Gson gson;
    private final ValidationUtil validator;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, FileUtil fileUtil, Gson gson, ValidationUtil validator, ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        String clientsFileContent = this.fileUtil.readFile(CLIENTS_JSON_FILE_PATH);
        return clientsFileContent;
    }

    @Override
    public String importClients(String clients) {
        StringBuilder importResult = new StringBuilder();

        ClientImportDto[] clientImportDtos = this.gson.fromJson(clients, ClientImportDto[].class);

        for (ClientImportDto clientImportDto : clientImportDtos) {

            if (!this.validator.isValid(clientImportDto)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            String employeeFirstName = clientImportDto.getAppointedEmployee().split("\\s+")[0];
            String employeeLastName = clientImportDto.getAppointedEmployee().split("\\s+")[1];
            Employee employee = this.employeeRepository.findByFirstNameAndLastName(employeeFirstName, employeeLastName).orElse(null);
            if (employee == null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }

            if (this.clientRepository.existsByFullName(String.format("%s %s", clientImportDto.getFirstName(), clientImportDto.getLastName()))){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());

                continue;
            }
            Client clientEntity = modelMapper.map(clientImportDto, Client.class);
            clientEntity.setFullName(String.format("%s %s", clientImportDto.getFirstName(), clientImportDto.getLastName()));
            clientEntity.getEmployees().add(employee);

            importResult.append(String.format("Successfully imported Client – %s.", clientEntity.getFullName()));

            this.clientRepository.saveAndFlush(clientEntity);

        }

        return importResult.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        StringBuilder exportResult = new StringBuilder();

        Client familyGuy = this.clientRepository.findAll().stream()
                .filter(c -> c.getBankAccount() != null)
                .filter(c -> !c.getBankAccount().getCards().isEmpty())
                .sorted((c1, c2)->{
            return Integer.compare(c2.getBankAccount().getCards().size(), c1.getBankAccount().getCards().size());
        })
                .collect(Collectors.toList()).get(0);

        exportResult.append(String.format("Full Name: %s", familyGuy.getFullName())).append(System.lineSeparator())
                    .append(String.format("Age: %d", familyGuy.getAge())).append(System.lineSeparator())
                    .append(String.format("Bank Account: %s", familyGuy.getBankAccount().getAccountNumber())).append(System.lineSeparator());


        for (Card card : familyGuy.getBankAccount().getCards()) {
            exportResult.append(String.format(" Card Number: %s", card.getCardNumber())).append(System.lineSeparator())
                    .append(String.format(" Card Status: %s", card.getCardStatus())).append(System.lineSeparator()).append(System.lineSeparator());

        }
        return exportResult.toString().trim();
    }
}
