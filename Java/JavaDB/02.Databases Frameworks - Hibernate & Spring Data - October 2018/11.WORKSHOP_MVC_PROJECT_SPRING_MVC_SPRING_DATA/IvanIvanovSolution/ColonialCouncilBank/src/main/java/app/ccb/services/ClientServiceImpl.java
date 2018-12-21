package app.ccb.services;

import app.ccb.constants.ResourcesPath;
import app.ccb.domain.dtos.ClientDto;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.ClientRepository;
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
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             Gson gson,
                             ModelMapper mapper,
                             ValidationUtil validationUtil,
                             FileUtil fileUtil) {
        this.clientRepository = clientRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean clientsAreImported() {

        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() {
        return this.fileUtil.readFile(ResourcesPath.JSON.CLIENTS);
    }

    @Override
    public String importClients(String clients) {
        List<String> responseMessages = new ArrayList<>();
        try {

            ClientDto[] clientDtos = this.gson.fromJson(clients, ClientDto[].class);
            List<Client> successCreated = new ArrayList<>(clientDtos.length);
            for (ClientDto clientDto : clientDtos) {

                if (this.validationUtil.isValid(clientDto)) {
                    Client client = this.mapper.map(clientDto, Client.class);
                    Client cl = this.clientRepository.findFirstByFullName(client.getFullName());
                    if (cl == null) {
                        successCreated.add(client);
                    } else {
                        System.out.println();
                    }
                    responseMessages.add(String.format("Successfully imported %s – %s.", "Client", client.getFullName()));
                } else {
                    responseMessages.add("Error: Incorrect Data!");
                }
            }
            this.clientRepository.saveAll(successCreated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessages.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String exportFamilyGuy() {
        // TODO : Implement Me
        return null;
    }
}
