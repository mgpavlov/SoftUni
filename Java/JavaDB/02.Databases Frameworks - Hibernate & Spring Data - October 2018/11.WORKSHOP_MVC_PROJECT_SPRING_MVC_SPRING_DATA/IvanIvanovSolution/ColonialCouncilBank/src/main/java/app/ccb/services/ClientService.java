package app.ccb.services;

public interface ClientService {

    Boolean clientsAreImported();

    String readClientsJsonFile();

    String importClients(String clients);

    String exportFamilyGuy();
}
