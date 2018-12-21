package cars.controllers;

import cars.dto.bindings.CustomerImportDto;
import cars.dto.bindings.CustomersImportXmlDto;
import cars.dto.views.customer.CustomerTotalSalesViewDto;
import cars.dto.views.customer.CustomersTotalSalesXmlViewDto;
import cars.dto.views.customer.OrderedCustomersViewDto;
import cars.dto.views.customer.OrderedCustomersXmlViewDto;
import cars.serializers.Serializer;
import cars.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;


@Controller
public class CustomerController {
    private static final String CUSTOMERS_JSON_INPUT_PATH = "/json/input/customers.json";
    private static final String CUSTOMERS_XML_INPUT_PATH = "/xml/input/customers.xml";
    private static final String ORDERED_CUSTOMERS_JSON_OUTPUT_PATH = "/src/main/resources/json/output/ordered-customer.json";
    private static final String ORDERED_CUSTOMERS_XML_OUTPUT_PATH = "src/main/resources/xml/output/ordered-customer.xml";
    private static final String CUSTOMERS_TOTAL_SALES_JSON_OUTPUT_PATH = "/src/main/resources/json/output/customer-total-sales.json";
    private static final String CUSTOMERS_TOTAL_SALES_XML_OUTPUT_PATH = "src/main/resources/xml/output/customer-total-sales.xml";

    private final CustomerService customerService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public CustomerController(CustomerService customerService, @Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer) {
        this.customerService = customerService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importCustomersFromJson() {
        CustomerImportDto[] customerImportDtos = this.jsonSerializer.deserialize(CustomerImportDto[].class, CUSTOMERS_JSON_INPUT_PATH);
        this.customerService.createMany(Arrays.asList(customerImportDtos));
    }

    public void importCustomersFromXml() {
        CustomersImportXmlDto customersImportXmlDto = this.xmlSerializer.deserialize(CustomersImportXmlDto.class, CUSTOMERS_XML_INPUT_PATH);
        this.customerService.createMany(customersImportXmlDto.getCustomerImportDtos());
    }

    public void exportOrderedCustomersToJson() {
        List<OrderedCustomersViewDto> orderedCustomersViewDtos = this.customerService.orderedCustomers();
        this.jsonSerializer.serialize(orderedCustomersViewDtos, ORDERED_CUSTOMERS_JSON_OUTPUT_PATH);
    }

    public void exportOrderedCustomersToXml() {
        List<OrderedCustomersViewDto> orderedCustomersViewDtos = this.customerService.orderedCustomers();
        OrderedCustomersXmlViewDto orderedCustomersXmlViewDto = new OrderedCustomersXmlViewDto();
        orderedCustomersXmlViewDto.setOrderedCustomersViewDtos(orderedCustomersViewDtos);
        this.xmlSerializer.serialize(orderedCustomersXmlViewDto, ORDERED_CUSTOMERS_XML_OUTPUT_PATH);
    }

    public void exportTotalSalesByCustomerToJson() {
        List<CustomerTotalSalesViewDto> customerTotalSalesViewDtos = this.customerService.getTotalSalesByCustomer();
        this.jsonSerializer.serialize(customerTotalSalesViewDtos, CUSTOMERS_TOTAL_SALES_JSON_OUTPUT_PATH);
    }

    public void exportTotalSalesByCustomerToXml() {
        List<CustomerTotalSalesViewDto> customerTotalSalesViewDtos = this.customerService.getTotalSalesByCustomer();
        CustomersTotalSalesXmlViewDto customersTotalSalesXmlViewDto = new CustomersTotalSalesXmlViewDto();
        customersTotalSalesXmlViewDto.setCustomerTotalSalesViewDtos(customerTotalSalesViewDtos);
        this.xmlSerializer.serialize(customersTotalSalesXmlViewDto, CUSTOMERS_TOTAL_SALES_XML_OUTPUT_PATH);
    }
}
