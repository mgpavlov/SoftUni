package products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import products.dto.bindings.UserImportDto;
import products.dto.bindings.UsersImportXmlDto;
import products.dto.views.user.SummaryUsersSoldProductsViewDto;
import products.dto.views.user.UserNameSoldProductsViewDto;
import products.dto.views.user.UsersNameSoldProductsXmlViewDto;
import products.serializers.Serializer;
import products.services.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    private static final String USERS_JASON_PATH = "/json/input/users.json";
    private static final String USERS_XML_PATH = "/xml/input/users.xml";
    private static final String SOLD_PRODUCTS_JASON_OUTPUT_PATH = "src/main/resources/json/output/soldProducts.json";
    private static final String SOLD_PRODUCTS_XML_OUTPUT_PATH = "src/main/resources/xml/output/soldProducts.xml";
    private static final String USERS_PRODUCTS_JSON_OUTPUT_PATH = "src/main/resources/json/output/users-and-products.json";
    private static final String USERS_PRODUCTS_XML_OUTPUT_PATH = "src/main/resources/xml/output/users-and-products.xml";

    private final UserService userService;
    private final Serializer xmlSerializer;
    private final Serializer jsonSerializer;

    @Autowired
    public UserController(UserService userService, @Qualifier(value = "XmlSerializer") Serializer xmlSerializer, @Qualifier(value = "JsonSerializer") Serializer jsonSerializer) {
        this.userService = userService;
        this.xmlSerializer = xmlSerializer;
        this.jsonSerializer = jsonSerializer;
    }

    public void importUsersFromJson() throws IOException, InstantiationException, IllegalAccessException {
        UserImportDto[] userImportDtos = this.jsonSerializer.deserialize(UserImportDto[].class, USERS_JASON_PATH);
        this.userService.createMany(Arrays.asList(userImportDtos));
    }

    public void importUsersFromXml() throws IllegalAccessException, InstantiationException {
        UsersImportXmlDto usersImportXmlDto = this.xmlSerializer.deserialize(UsersImportXmlDto.class, USERS_XML_PATH);
        this.userService.createMany(usersImportXmlDto.getUserImportDtos());
    }

    public void exportUsersWithSoldProductsToJson() throws InstantiationException, IllegalAccessException, IOException {
        List<UserNameSoldProductsViewDto> users = this.userService.getUsersBySoldProducts();
        this.jsonSerializer.serialize(users, SOLD_PRODUCTS_JASON_OUTPUT_PATH);
    }

    public void exportUsersWithSoldProductsToXml() throws IllegalAccessException, InstantiationException {
        List<UserNameSoldProductsViewDto> users = this.userService.getUsersBySoldProducts();
        UsersNameSoldProductsXmlViewDto usersNameSoldProductsXmlViewDto = new UsersNameSoldProductsXmlViewDto();
        usersNameSoldProductsXmlViewDto.setUserNameSoldProductsViewDtos(users);
        this.xmlSerializer.serialize(usersNameSoldProductsXmlViewDto, SOLD_PRODUCTS_XML_OUTPUT_PATH);
    }

    public void exportUsersAndProductsToJson() throws IOException, InstantiationException, IllegalAccessException {
        SummaryUsersSoldProductsViewDto summary = this.userService.findUsers();
        this.jsonSerializer.serialize(summary, USERS_PRODUCTS_JSON_OUTPUT_PATH);
    }

    public void exportUsersAndProductsToXml() throws IllegalAccessException, InstantiationException {
        SummaryUsersSoldProductsViewDto summary = this.userService.findUsers();
        this.xmlSerializer.serialize(summary, USERS_PRODUCTS_XML_OUTPUT_PATH);
    }
}