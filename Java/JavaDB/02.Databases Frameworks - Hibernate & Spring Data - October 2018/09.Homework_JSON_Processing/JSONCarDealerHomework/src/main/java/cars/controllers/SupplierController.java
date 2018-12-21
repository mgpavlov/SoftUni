package cars.controllers;

import cars.dto.bindings.SupplierImportDto;
import cars.dto.bindings.SuppliersImportXmlDto;
import cars.dto.views.supplier.LocalSupplierViewDto;
import cars.dto.views.supplier.LocalSuppliersXmlViewDto;
import cars.serializers.Serializer;
import cars.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class SupplierController {
    private static final String SUPPLIERS_JSON_INPUT_PATH = "/json/input/suppliers.json";
    private static final String SUPPLIERS_XML_INPUT_PATH = "/xml/input/suppliers.xml";
    private static final String LOCAL_SUPPLIERS_JSON_OUTPUT_PATH = "/src/main/resources/json/output/local-suppliers.json";
    private static final String LOCAL_SUPPLIERS_XML_OUTPUT_PATH = "src/main/resources/xml/output/local-suppliers.xml";

    private final SupplierService supplierService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public SupplierController(SupplierService supplierService, @Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer) {
        this.supplierService = supplierService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importSuppliersFromJson() {
        SupplierImportDto[] supplierImportDtos = this.jsonSerializer.deserialize(SupplierImportDto[].class, SUPPLIERS_JSON_INPUT_PATH);
        this.supplierService.createMany(Arrays.asList(supplierImportDtos));
    }

    public void importSuppliersFromXml() {
        SuppliersImportXmlDto suppliersImportXmlDto = this.xmlSerializer.deserialize(SuppliersImportXmlDto.class, SUPPLIERS_XML_INPUT_PATH);
        this.supplierService.createMany(suppliersImportXmlDto.getSupplierImportDtos());
    }

    public void exportLocalSuppliersToJson() {
        List<LocalSupplierViewDto> localSuppliers = this.supplierService.getLocalSuppliers();
        this.jsonSerializer.serialize(localSuppliers, LOCAL_SUPPLIERS_JSON_OUTPUT_PATH);
    }

    public void exportLocalSuppliersToXml() {
        List<LocalSupplierViewDto> localSuppliers = this.supplierService.getLocalSuppliers();
        LocalSuppliersXmlViewDto localSuppliersXmlViewDto = new LocalSuppliersXmlViewDto();
        localSuppliersXmlViewDto.setLocalSupplierViewDtos(localSuppliers);
        this.xmlSerializer.serialize(localSuppliersXmlViewDto, LOCAL_SUPPLIERS_XML_OUTPUT_PATH);
    }
}
